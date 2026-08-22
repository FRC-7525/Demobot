package frc.robot.Manager;

// import frc.robot.Subsystems.AutoAlign.AutoAlign;
import frc.robot.Subsystems.Climber.ClimberStates;
import frc.robot.Subsystems.Intake.IntakeStates;
import frc.robot.Subsystems.Shooter.ShooterStates;

public enum ManagerStates {
	// climber should have intake in
	// CLIMBOUT(ClimberStates.GOINGOUT, IntakeStates.IDLE, ShooterStates.IDLE, "CLIMBOUT"),
	// CLIMBIN(ClimberStates.COMINGIN, IntakeStates.IDLE, ShooterStates.IDLE, "CLIMBIN"),
	INIDLE(ClimberStates.IDLE, IntakeStates.IDLE, ShooterStates.IDLE, "REALLYIDLE"),
	IDLE(ClimberStates.IDLE, IntakeStates.IDLE, ShooterStates.IDLE, "IDLE"),
	INTAKING(ClimberStates.IDLE, IntakeStates.INTAKE, ShooterStates.IDLE, "INTAKING"),
	// CLIMBAUTO(ClimberStates.GOINGOUT, IntakeStates.IDLE, ShooterStates.IDLE, "CLIMBAUTO"),
	// DYNAMICSHOT(ClimberStates.IDLE, In
	// takeStates.INTAKING_OUT, PassthroughStates.PASS, ShooterStates.DYNAMICSHOOT, "DYNAMICSHOT"),
	// LONGSHOT(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.PASS, ShooterStates.LONGPASS, "LONGPASS"),
	// SPINUP(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.IDLE, ShooterStates.MIDSHOOT, "SPINUP"),;
	FIXEDSHOT(ClimberStates.IDLE, IntakeStates.AGITATE, ShooterStates.MIDSHOOT, "MIDSHOOT"),
	WINDUP(ClimberStates.IDLE, IntakeStates.IDLE, ShooterStates.MIDSHOOT, "WINDUP"),
	OUTTAKE(ClimberStates.IDLE, IntakeStates.OUTTAKE, ShooterStates.IDLE, "OUTTAKE"),
	// -------------------------------------------------------- AUTO STATES --------------------------------------------------------
	// SHOOT_AUTO(ClimberStates.IDLE, IntakeStates.PASS, ShooterStates.MIDSHOOT, "SHOOT_AUTO"),
	// WINDUP_AUTO(ClimberStates.IDLE, IntakeStates.IDLE, ShooterStates.MIDSHOOT, "WINDUP_AUTO"),
	// REVERSE_SHOOT(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.IDLE, ShooterStates.REVERSESHOOT, "REVERSE_SHOOT");
	REVERSE_PASS(ClimberStates.IDLE, IntakeStates.OUTTAKE, ShooterStates.IDLE, "REVERSE_PASS");

	private final String stateString;
	// private final IntakeStates intakeState;
	private final ShooterStates shooterState;
	private final ClimberStates climberState;
	private final IntakeStates intakeState;

	ManagerStates(ClimberStates climberState, IntakeStates intakeState, ShooterStates shooterState, String stateString) {
		this.climberState = climberState;
		// this.intakeState = intakeState;
		this.intakeState = intakeState;
		this.shooterState = shooterState;
		this.stateString = stateString;
	}

	public ClimberStates getClimberState() {
		return climberState;
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
