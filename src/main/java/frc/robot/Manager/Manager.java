package frc.robot.Manager;

import static frc.robot.GlobalConstants.Controllers.*;
import static frc.robot.Manager.ManagerConstants.*;
import static frc.robot.Manager.ManagerStates.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.Intake.Intake;
import frc.robot.Subsystems.Shooter.Shooter;
import org.littletonrobotics.junction.Logger;

public class Manager {

	Intake intake;
	Shooter shooter;
	ManagerStates robotstate;
	ManagerStates goalState; // Used for tracking shooter power level

	private static Manager instance;

	private Manager() {
		intake = Intake.getInstance();
		shooter = Shooter.getInstance();

		robotstate = IN_IDLE;
		goalState = LOWSHOT;
	}

	// Makes sure that there is only ONE instance of the Shooter class, and if there isn't, it creates a new one (this is a singleton pattern)
	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	public void periodic() {
		intake.setState(getState().getIntakeState());
		shooter.setState(getState().getShooterState());
		Logger.recordOutput("Manager State", robotstate.getStateString());

		intake.periodic();
		shooter.periodic();
		SmartDashboard.putString("Manager State", robotstate.getStateString());

		if (DRIVER_CONTROLLER.getStartButtonPressed()) {
			robotstate = IN_IDLE;
			return;
		}
		if (robotstate == IN_IDLE || robotstate == OUT_IDLE || robotstate == INTAKING) {
			if (OPERATOR_CONTROLLER.getPOV() == UP) {
				goalState = HIGHSHOT;
			} else if (OPERATOR_CONTROLLER.getPOV() == DOWN) {
				goalState = LOWSHOT;
			} else if (OPERATOR_CONTROLLER.getPOV() == LEFT) {
				goalState = MIDSHOT;
			} else if (OPERATOR_CONTROLLER.getPOV() == RIGHT) {
				goalState = MIDSHOT;
			}
		}

		switch (robotstate) {
			case IN_IDLE:
				if (DRIVER_CONTROLLER.getLeftBumperButtonPressed()) {
					robotstate = INTAKING;
				} else if (DRIVER_CONTROLLER.getXButtonPressed()) {
					robotstate = OUT_IDLE;
				} else if (DRIVER_CONTROLLER.getRightBumperButtonPressed()) {
					robotstate = goalState;
				}
				break;
			case OUT_IDLE:
				if (DRIVER_CONTROLLER.getLeftBumperButtonPressed()) {
					robotstate = INTAKING;
				} else if (DRIVER_CONTROLLER.getRightBumperButtonPressed()) {
					robotstate = goalState;
				} else if (DRIVER_CONTROLLER.getBackButtonPressed()) {
					robotstate = REVERSE_PASS;
				} else if (DRIVER_CONTROLLER.getXButtonPressed()) {
					robotstate = IN_IDLE;
				} 
				break;
			case INTAKING:
				if (DRIVER_CONTROLLER.getLeftBumperButtonPressed()) {
					robotstate = OUT_IDLE;
				} else if (DRIVER_CONTROLLER.getRightBumperButtonPressed()) {
					robotstate = goalState;
				}
				break;
			case HIGHSHOT:
			case MIDSHOT:
			case LOWSHOT:
				if (DRIVER_CONTROLLER.getLeftBumperButtonPressed()) {
					robotstate = INTAKING;
				} else if (DRIVER_CONTROLLER.getRightBumperButtonPressed()) {
					robotstate = OUT_IDLE;
				}
				if (DRIVER_CONTROLLER.getAButtonPressed()) {
					// SET to the AGITATE version of this state
					switch (robotstate) {
						case HIGHSHOT:
							robotstate = HIGHSHOT_AGITATE;
							break;
						case MIDSHOT:
							robotstate = MIDSHOT_AGITATE;
							break;
						case LOWSHOT:
							robotstate = LOWSHOT_AGITATE;
							break;
						default:
							break;
					}
				}
				break;
			case HIGHSHOT_AGITATE:
			case MIDSHOT_AGITATE:
			case LOWSHOT_AGITATE:
				if (DRIVER_CONTROLLER.getLeftBumperButtonPressed()) {
					robotstate = INTAKING;
				} else if (DRIVER_CONTROLLER.getRightBumperButtonPressed()) {
					robotstate = OUT_IDLE;
				}
				if (DRIVER_CONTROLLER.getAButtonPressed()) {
					// SET to the non-AGITATE version of this state
					switch (robotstate) {
						case HIGHSHOT_AGITATE:
							robotstate = HIGHSHOT;
							break;
						case MIDSHOT_AGITATE:
							robotstate = MIDSHOT;
							break;
						case LOWSHOT_AGITATE:
							robotstate = LOWSHOT;
							break;
						default:
							break;
					}
				}
			case REVERSE_PASS:
				if (DRIVER_CONTROLLER.getBackButtonPressed()) {
					robotstate = OUT_IDLE;
				}
				break;
			default:
				robotstate = IN_IDLE;
				break;
		}
	}

	public ManagerStates getState() {
		return robotstate;
	}

	public void setState(ManagerStates newState) {
		robotstate = newState;
	}
}
