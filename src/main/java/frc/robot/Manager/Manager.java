package frc.robot.Manager;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.Subsystems.AutoAlign.AutoAlign;
import frc.robot.Subsystems.Climber.Climber;
import frc.robot.Subsystems.Drive.Drive;
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

        robotstate = IDLEINTAKEOUT;
        intakeOut = false;
    }

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

    public void periodic() {
        //Intaking * change driver controller to operator controller
        // if (operatorController.getYButtonPressed()) {
        //     intakeOut = !intakeOut;
        // }

        // if (intakeOut) {
        //     robotstate = IDLEINTAKEOUT;
        // } else if (intakeOut == false) {
        //     robotstate = IDLE;
        // }

        if (operatorController.getXButtonPressed()) {
        robotstate = INTAKING;
    } else if (operatorController.getXButtonReleased()) {
        robotstate = IDLEINTAKEOUT;
    }
        

        //Shooting
    // if (operatorController.getLeftTriggerAxis() > 0.1) {
    //     robotstate = DYNAMICSHOT;
    // } else if (operatorController.getLeftTriggerAxis() < 0.1) {
    //     robotstate = IDLE;
    // }
    // if (operatorController.getRightTriggerAxis() > 0.1) {
    //     robotstate = LONGSHOT;
    // } else if (operatorController.getRightTriggerAxis() < 0.1) {
    //     robotstate = IDLE;

    // }
    if (operatorController.getRightBumperButtonPressed()) {
        robotstate = FIXEDSHOT;
    } else if (operatorController.getRightBumperButtonReleased()) {
        robotstate = IDLEINTAKEOUT;
    }
    //Climbing
    // if (driverController.getYButtonPressed()) {
    //     robotstate = CLIMBPREP;
    // } else if (driverController.getYButtonReleased()) {
    //     robotstate = CLIMBLV1;
    // }else if (driverController.getAButtonPressed()) {
    //     robotstate = CLIMBLV2;
    // }
    //  else if (driverController.getYButtonPressed() || driverController.getAButtonReleased()) {
    //     robotstate = IDLE;
    // }
    //AutoAlign
    // if(driverController.getLeftBumperButtonPressed()) {
        
    // } else if (driverController.getLeftBumperButtonReleased()) {
    //     robotstate = IDLE;
    // }
        intake.setState(getState().getIntakeState());
        passthrough.setState(getState().getPassthroughState());
        shooter.setState(getState().getShooterState());
        climber.setState(getState().getClimberState());
        // autoalign.getAutoAlignState();
        Logger.recordOutput("Manager State", robotstate.getStateString());
        // Logger.recordOutput("AutoAlign State", autoalign.getInstance().getAutoAlignState());

        Intake.getInstance().periodic();
        Passthrough.getInstance().periodic();
        Shooter.getInstance().periodic();
        Climber.getInstance().periodic();
        Drive.getInstance().periodic();

        SmartDashboard.putString("Manager State", robotstate.getStateString());

        
    }

    public ManagerStates getState() {
        return robotstate;
    }

}
