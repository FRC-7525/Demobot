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

import javax.sql.rowset.RowSetProvider;

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
        // SHOOTING
    if (driverController.getRightTriggerAxis() > 0.1) {
        robotstate = FIXEDSHOT;
    } else if (driverController.getRightTriggerAxis() < 0.1) {
        robotstate = IDLE;
    }

    if (driverController.getLeftTriggerAxis() > 0.11) {
        robotstate = DYNAMICSHOT;
    } else if (driverController.getLeftTriggerAxis() < 0.1) {
        robotstate = IDLE;
    }

    if (driverController.getRightBumperButtonPressed()) {
        robotstate = LONGSHOT;
    } else if (driverController.getRightBumperButtonReleased()) {
        robotstate = IDLE;
    }

    // INTAKING 
    if (driverController.getXButton()) {
        robotstate = INTAKING;
    } else if (driverController.getXButton()) {
        robotstate = IDLE;
    }

    if (operatorController.getXButton()) {
        robotstate = INTAKEOUT;
    } else if (operatorController.getXButton()) {
        robotstate = IDLE;
    }

    // CLIMBING
    if (operatorController.getYButton()) {
        robotstate = CLIMBPREP;
    } else if (operatorController.getYButton()) {
        robotstate = CLIMBLV1;
    } else if (operatorController.getYButton()) {
        robotstate = CLIMBLV2;
    }







        intake.setState(getState().getIntakeState());
        passthrough.setState(getState().getPassthroughState());
        shooter.setState(getState().getShooterState());
        climber.setState(getState().getClimberState());
        // autoalign.getAutoAlignState();
        Logger.recordOutput("Manager State", robotstate.getStateString());
        // Logger.recordOutput("AutoAlign State", autoalign.getInstance().getAutoAlignState());

        //Intake.getInstance().periodic();
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
