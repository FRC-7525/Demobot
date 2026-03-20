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
    //AutoAlign autoalign;
    boolean intakeOut;

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

    public void periodic() {
        intake.setState(getState().getIntakeState());
        passthrough.setState(getState().getPassthroughState());
        shooter.setState(getState().getShooterState());
        climber.setState(getState().getClimberState());
        // autoalign.getAutoAlignState();
        Logger.recordOutput("Manager State", robotstate.getStateString());
        // Logger.recordOutput("AutoAlign State", autoalign.getInstance().getAutoAlignState());

        intake.periodic();
        passthrough.periodic();
        shooter.periodic();
        climber.periodic();

        SmartDashboard.putString("Manager State", robotstate.getStateString());

        if (robotstate == WINDUP && shooter.atSpeed()) {
            robotstate = FIXEDSHOT;
        }

        // IDLE All
        if(operatorController.getYButtonPressed()) {
             if(robotstate == INIDLE) {
                robotstate = IDLE;
             } else {
                robotstate = INIDLE;
             }
        }
        // REVERSE PASS
        if(operatorController.getXButtonPressed()) {
            if(robotstate == REVERSE_PASS) {
                robotstate = IDLE;
             } else {
                robotstate = REVERSE_PASS;
             }
        }
       
        
            // SHOOTING Fixed
        if (driverController.getRightBumperButtonPressed()) {
            if(robotstate == IDLE) {
                robotstate = WINDUP;
            }
            else if (robotstate == WINDUP) {
                robotstate = FIXEDSHOT;
            }
            else{
                robotstate = IDLE;
            }
        }
        
        // SHOOTING DYNAMIC
        // if (driverController.getAButtonPressed()) {
        //     if(robotstate == IDLE) {
        //         robotstate = DYNAMICSHOT;
        //     }
        //     else if(robotstate == DYNAMICSHOT) {
        //         robotstate = IDLE;
        //     }
        // }

        // // SHOOTING LONG
        // if (driverController.getRightBumperButtonPressed()) {
        //     if(robotstate == IDLE) {
        //         robotstate = LONGSHOT;
        //     }
        //     else if(robotstate == LONGSHOT) {
        //         robotstate = IDLE;
        //     }
        // }

        // INTAKING 
        if (driverController.getXButtonPressed()) {
            if(robotstate == IDLE) {
                robotstate = INTAKING;
                return;
            } else if(robotstate == INTAKING) {
                robotstate = IDLE;
                return;
            }

        } 

    

        //OPERATOR OVERRIDE INTAKE OUT
        if (operatorController.getBButtonPressed()) {
            if(robotstate == INIDLE) {
                robotstate = IDLE;
            } else {robotstate = INIDLE;}
        } 

        
        if (operatorController.getLeftTriggerAxis() > 0.1 || operatorController.getRightTriggerAxis() > 0.1) {
            robotstate = CLIMBIN;
        }
        if (robotstate
             == CLIMBIN) {
            if (operatorController.getLeftTriggerAxis() > 0.1) {
                climber.setSpeed(-1);
            } else if (operatorController.getRightTriggerAxis() > 0.1) {
                climber.setSpeed(1);
            } else {
                climber.setSpeed(0);
            }
        }        
    }

    public ManagerStates getState() {
        return robotstate;
    }

    public void setState(ManagerStates newState) {
        robotstate = newState;
    }
}
