package frc.robot.Manager;

import frc.robot.Subsystems.Intake.IntakeStates;
import frc.robot.Subsystems.LEDs.LEDStates;
import frc.robot.Subsystems.Shooter.ShooterStates;

public enum ManagerStates {
	IN_IDLE(IntakeStates.IN_IDLE, ShooterStates.IDLE, "INTAKE IN IDLE", LEDStates.IDLE),
	OUT_IDLE(IntakeStates.OUT_IDLE, ShooterStates.IDLE, "INTAKE OUT IDLE", LEDStates.IDLE),
	INTAKING(IntakeStates.INTAKE, ShooterStates.IDLE, "INTAKING", LEDStates.INTAKING),
	LOWSHOT(IntakeStates.OUT_IDLE, ShooterStates.LOWSHOOT, "LOWSHOOT", LEDStates.IDLE),
	MIDSHOT(IntakeStates.OUT_IDLE, ShooterStates.MIDSHOOT, "MIDSHOOT", LEDStates.IDLE),
	HIGHSHOT(IntakeStates.OUT_IDLE, ShooterStates.HIGHSHOOT, "HIGHSHOOT", LEDStates.IDLE),
	LOWSHOT_AGITATE(IntakeStates.AGITATE, ShooterStates.LOWSHOOT, "LOWSHOOT AGITATE", LEDStates.IDLE),
	MIDSHOT_AGITATE(IntakeStates.AGITATE, ShooterStates.MIDSHOOT, "MIDSHOOT AGITATE", LEDStates.IDLE),
	HIGHSHOT_AGITATE(IntakeStates.AGITATE, ShooterStates.HIGHSHOOT, "HIGHSHOOT AGITATE", LEDStates.IDLE),
	REVERSE_PASS(IntakeStates.OUTTAKE, ShooterStates.IDLE, "REVERSE_PASS", LEDStates.OFF);
	private final String stateString;
	private final ShooterStates shooterState;
	private final IntakeStates intakeState;
	private final LEDStates ledState;

	ManagerStates(IntakeStates intakeState, ShooterStates shooterState, String stateString, LEDStates ledState) {
		this.intakeState = intakeState;
		this.shooterState = shooterState;
		this.stateString = stateString;
		this.ledState = ledState;
	}

	public IntakeStates getIntakeState() {
		return intakeState;
	}

	public ShooterStates getShooterState() {
		return shooterState;
	}

	public String getStateString() {
		return stateString;
	}

	public LEDStates getLEDState() {
		return ledState;
	}
}
