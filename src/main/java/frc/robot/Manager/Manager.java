package frc.robot.Manager;

import static frc.robot.Manager.ManagerStates.*;
import static frc.robot.GlobalConstants.Controllers.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.Intake.Intake;
import frc.robot.Subsystems.Intake.IntakeStates;
import frc.robot.Subsystems.Shooter.Shooter;
import org.littletonrobotics.junction.Logger;

public class Manager {
	Intake intake;
	Shooter shooter;
	ManagerStates robotstate;

	private static Manager instance;

	private Manager() {
		intake = Intake.getInstance();
		shooter = Shooter.getInstance();

		robotstate = IN_IDLE;
	}

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
			continue;
		}

		switch (robotstate) {
			case IN_IDLE:
				if (OPERATOR_CONTROLLER.getXButtonPressed()) {
					robotstate = REVERSE_PASS;
				}

				if (DRIVER_CONTROLLER.getYButtonPressed()) {
					robotstate = INTAKING;
				}

				if (OPERATOR_CONTROLLER.getAButtonPressed()) {
					robotstate = IN_IDLE;
				}
				break;
			case INTAKING:
				if (DRIVER_CONTROLLER.getYButtonPressed()) {
					robotstate = IDLE;
				}
				break;
			case REVERSE_PASS:
				if (OPERATOR_CONTROLLER.getXButtonPressed()) {
					robotstate = IDLE;
				}
				break;
			default:
				robotstate = IDLE;
				break;
		}
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
