package frc.robot.Manager;

import frc.robot.Subsystems.Intake.IntakeStates;
import frc.robot.Subsystems.Shooter.ShooterStates;

public enum ManagerStates {
	IN_IDLE(IntakeStates.IN_IDLE, ShooterStates.IDLE, "INTAKE IN IDLE"),
	OUT_IDLE(IntakeStates.OUT_IDLE, ShooterStates.IDLE, "INTAKE OUT IDLE"),
	INTAKING(IntakeStates.INTAKE, ShooterStates.IDLE, "INTAKING"),
	LOWSHOT(IntakeStates.OUT_IDLE, ShooterStates.LOWSHOOT, "LOWSHOOT"),
	MIDSHOT(IntakeStates.OUT_IDLE, ShooterStates.MIDSHOOT, "MIDSHOOT"),
	HIGHSHOT(IntakeStates.OUT_IDLE, ShooterStates.HIGHSHOOT, "HIGHSHOOT"),
	LOWSHOT_AGITATE(IntakeStates.AGITATE, ShooterStates.LOWSHOOT, "LOWSHOOT AGITATE"),
	MIDSHOT_AGITATE(IntakeStates.AGITATE, ShooterStates.MIDSHOOT, "MIDSHOOT AGITATE"),
	HIGHSHOT_AGITATE(IntakeStates.AGITATE, ShooterStates.HIGHSHOOT, "HIGHSHOOT AGITATE"),
	REVERSE_PASS(IntakeStates.OUTTAKE, ShooterStates.IDLE, "REVERSE_PASS");
	private final String stateString;
	private final ShooterStates shooterState;
	private final IntakeStates intakeState;

	ManagerStates(IntakeStates intakeState, ShooterStates shooterState, String stateString) {
		this.intakeState = intakeState;
		this.shooterState = shooterState;
		this.stateString = stateString;
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
}
