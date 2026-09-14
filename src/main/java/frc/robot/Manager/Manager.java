package frc.robot.Manager;

import static frc.robot.Manager.ManagerConstants.*;
import static frc.robot.Manager.ManagerStates.*;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.Climber.Climber;
import frc.robot.Subsystems.Intake.Intake;
import frc.robot.Subsystems.Intake.IntakeStates;
import frc.robot.Subsystems.LEDs.LEDs;
import frc.robot.Subsystems.Shooter.Shooter;
import org.littletonrobotics.junction.Logger;

public class Manager {

	Climber climber;
	Intake intake;
	IntakeStates passthrough;
	Shooter shooter;
	LEDs leds;
	ManagerStates robotstate;
	private boolean intakeOut;

	private XboxController driverController = new XboxController(DRIVER_CONTROLLER_PORT);
	private XboxController operatorController = new XboxController(OPERATOR_CONTROLLER_PORT);

	private static Manager instance;

	private Manager() {
		//climber = Climber.getInstance();
		//intake = Intake.getInstance();
		//shooter = Shooter.getInstance();
		leds = LEDs.getInstance();

		robotstate = IDLE;
		intakeOut = true;
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
		//intake.setState(getState().getIntakeState());
		//shooter.setState(getState().getShooterState());
		//climber.setState(getState().getClimberState());
		leds.setState(getState().getLedStates());
		Logger.recordOutput("Manager State", robotstate.getStateString());

		//intake.periodic();
		//intake.periodic();
		//shooter.periodic();
		//climber.periodic();
		leds.periodic();

		SmartDashboard.putString("Manager State", robotstate.getStateString());

		switch (robotstate) {
			case IDLE:
				if (operatorController.getXButtonPressed()) {
					robotstate = REVERSE_PASS;
				}

				if (driverController.getRightBumperButtonPressed()) {
					robotstate = WINDUP;
				}

				if (driverController.getYButtonPressed()) {
					robotstate = INTAKING;
				}

				if (operatorController.getAButtonPressed()) {
					robotstate = INIDLE;
				}
				// if (operatorController.getLeftTriggerAxis() > 0.1) {
				// 	robotstate = CLIMBIN;
				// 	intakeOut = true;
				// }

				// if (operatorController.getRightTriggerAxis() > 0.1) {
				// 	robotstate = CLIMBOUT;
				// 	intakeOut = true;
				// }
				break;
			case OUTTAKE:
				if (operatorController.getBButtonPressed()) {
					robotstate = IDLE;
				}
				break;
			case INTAKING:
				if (driverController.getYButtonPressed()) {
					robotstate = IDLE;
				}

				break;
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
			case INIDLE:
				intakeOut = true;
				if (operatorController.getBButtonPressed()) {
					robotstate = IDLE;
				}
				// 	if (operatorController.getLeftTriggerAxis() > 0.1) {
				// 		robotstate = CLIMBIN;
				// 		intakeOut = true;
				// 	}

				// 	if (operatorController.getRightTriggerAxis() > 0.1) {
				// 		robotstate = CLIMBOUT;
				// 		intakeOut = true;
				// 	}
				// 	break;
				// case CLIMBIN:
				// 	intakeOut = true;
				// 	climber.setSpeed(-0.25);
				// 	if (operatorController.getLeftTriggerAxis() < 0.1) {
				// 		robotstate = INIDLE;
				// 	}
				// 	break;
				// case CLIMBAUTO:
				// 	intakeOut = true;
				// 	break;
				// case CLIMBOUT:
				// 	intakeOut = true;
				// 	climber.setSpeed(0.25);
				// 	if (operatorController.getRightTriggerAxis() < 0.1) {
				// 		robotstate = INIDLE;
				// 	}
				break;
			default:
				//robotstate = IDLE;
				break;
		}
		// SmartDashboard.putBoolean("Intake/Intake Out", intakeOut);
	}

	public ManagerStates getState() {
		return robotstate;
	}

	public void setState(ManagerStates newState) {
		robotstate = newState;
	}

	public void setIntakeOut(boolean isOut) {
		intakeOut = isOut;
	}
}
