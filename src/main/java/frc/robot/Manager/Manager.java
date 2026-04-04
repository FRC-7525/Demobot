package frc.robot.Manager;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.Climber.Climber;
import frc.robot.Subsystems.Intake.Intake;
import frc.robot.Subsystems.Passthrough.Passthrough;
import frc.robot.Subsystems.Shooter.Shooter;

import static frc.robot.Manager.ManagerConstants.*;
import static frc.robot.Manager.ManagerStates.*;
import org.littletonrobotics.junction.Logger;

public class Manager {
    Climber climber;
    Intake intake;
    Passthrough passthrough;
    Shooter shooter;
    ManagerStates robotstate;
    private boolean intakeOut;

    private XboxController driverController = new XboxController(DRIVER_CONTROLLER_PORT);
    private XboxController operatorController = new XboxController(OPERATOR_CONTROLLER_PORT);

    private static Manager instance;

    private Manager() {
        climber = Climber.getInstance();
        intake = Intake.getInstance();
        passthrough = Passthrough.getInstance();
        shooter = Shooter.getInstance();

        robotstate = IDLE;
        intakeOut = false;
    }

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

    public boolean isIntakeOut() {
        return intakeOut;
    }

    public void periodic() {  
        // intake.setState(getState().getIntakeState());
        passthrough.setState(getState().getPassthroughState());
        shooter.setState(getState().getShooterState());
        climber.setState(getState().getClimberState());
        Logger.recordOutput("Manager State", robotstate.getStateString());

        intake.periodic();
        passthrough.periodic();
        shooter.periodic();
        climber.periodic();

        SmartDashboard.putString("Manager State", robotstate.getStateString());

        switch (robotstate) {
            case IDLE:
                robotstate = IDLE;

                // if (driverController.getYButtonPressed()) {
                //     robotstate = INTAKING;

                // }

                if (operatorController.getXButtonPressed()) {
                    robotstate = REVERSE_PASS;
                }

                if (driverController.getRightBumperButtonPressed()) {
                    robotstate = WINDUP;
                }

                if (operatorController.getLeftTriggerAxis() > 0.1) {
                    robotstate = CLIMBIN;
                }

                if (operatorController.getRightTriggerAxis() > 0.1) {
                    robotstate = CLIMBOUT;
                }
                break;
        
            // case INTAKING:
            //     if (driverController.getYButtonPressed()) {
            //         robotstate = IDLE;
            //     }

            //     break;
            case REVERSE_PASS:
                if (operatorController.getXButtonPressed()) {
                    robotstate = IDLE;
                }
                break;
            case WINDUP:
                if (driverController.getRightBumperButtonPressed()) {
                    robotstate = FIXEDSHOT;
                }
                break;
            case FIXEDSHOT:
                if (driverController.getRightBumperButtonPressed()) {
                    robotstate = IDLE;
                }
                break;
            // case INIDLE:
            //     if (operatorController.getBButtonPressed()) {
            //         robotstate = IDLE;
            //     }
            //     break;
            case CLIMBIN:
                if (operatorController.getLeftTriggerAxis() < 0.1) {
                    robotstate = IDLE;
                }
                break;
            case CLIMBOUT:
                if (operatorController.getRightTriggerAxis() < 0.1) {
                    robotstate = IDLE;
                }
                break;
            default:
                robotstate = IDLE;
                break;
        } 

        if (driverController.getAButtonPressed() || operatorController.getStartButtonPressed()) {
            robotstate = IDLE;
        }

        if (operatorController.getBButtonPressed()) {
            intakeOut = !intakeOut;
        }

        // SmartDashboard.putBoolean("Intake/Intake Out", intakeOut);
    }

    public ManagerStates getState() {
        return robotstate;
    }

    public void setState(ManagerStates newState) {
        robotstate = newState;
    }
}
