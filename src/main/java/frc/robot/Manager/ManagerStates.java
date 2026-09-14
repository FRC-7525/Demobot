package frc.robot.Manager;

// import frc.robot.Subsystems.AutoAlign.AutoAlign;
import frc.robot.Subsystems.Climber.ClimberStates;
import frc.robot.Subsystems.Intake.IntakeStates;
import frc.robot.Subsystems.Shooter.ShooterStates;
import frc.robot.Subsystems.LEDs.LEDStates;

public enum ManagerStates {
	// climber should have intake in
	// CLIMBOUT(ClimberStates.GOINGOUT, IntakeStates.IDLE, ShooterStates.IDLE, "CLIMBOUT"),
	// CLIMBIN(ClimberStates.COMINGIN, IntakeStates.IDLE, ShooterStates.IDLE, "CLIMBIN"),
	INIDLE(ClimberStates.IDLE, IntakeStates.IDLE, ShooterStates.IDLE, LEDStates.IDLE, "REALLYIDLE"),
	IDLE(ClimberStates.IDLE, IntakeStates.IDLE, ShooterStates.IDLE, LEDStates.IDLE, "IDLE"),
	INTAKING(ClimberStates.IDLE, IntakeStates.INTAKE, ShooterStates.IDLE, LEDStates.INTAKING, "INTAKING"),
	// CLIMBAUTO(ClimberStates.GOINGOUT, IntakeStates.IDLE, ShooterStates.IDLE, "CLIMBAUTO"),
	// DYNAMICSHOT(ClimberStates.IDLE, In
	// takeStates.INTAKING_OUT, PassthroughStates.PASS, ShooterStates.DYNAMICSHOOT, "DYNAMICSHOT"),
	// LONGSHOT(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.PASS, ShooterStates.LONGPASS, "LONGPASS"),
	// SPINUP(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.IDLE, ShooterStates.MIDSHOOT, "SPINUP"),;
	FIXEDSHOT(ClimberStates.IDLE, IntakeStates.AGITATE, ShooterStates.MIDSHOOT, LEDStates.SHOOTING, "MIDSHOOT"),
	WINDUP(ClimberStates.IDLE, IntakeStates.IDLE, ShooterStates.MIDSHOOT, LEDStates.SHOOTING, "WINDUP"),
	OUTTAKE(ClimberStates.IDLE, IntakeStates.OUTTAKE, ShooterStates.IDLE, LEDStates.INTAKING, "OUTTAKE"),
	// -------------------------------------------------------- AUTO STATES --------------------------------------------------------
	// SHOOT_AUTO(ClimberStates.IDLE, IntakeStates.PASS, ShooterStates.MIDSHOOT, "SHOOT_AUTO"),
	// WINDUP_AUTO(ClimberStates.IDLE, IntakeStates.IDLE, ShooterStates.MIDSHOOT, "WINDUP_AUTO"),
	// REVERSE_SHOOT(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.IDLE, ShooterStates.REVERSESHOOT, "REVERSE_SHOOT");
	REVERSE_PASS(ClimberStates.IDLE, IntakeStates.OUTTAKE, ShooterStates.IDLE, LEDStates.IDLE, "REVERSE_PASS");

	private final String stateString;
	// private final IntakeStates intakeState;
	private final ShooterStates shooterState;
	private final ClimberStates climberState;
	private final IntakeStates intakeState;
	private final LEDStates ledState;

	ManagerStates(ClimberStates climberState, IntakeStates intakeState, ShooterStates shooterState, LEDStates ledState, String stateString) {
		this.climberState = climberState;
		// this.intakeState = intakeState;
		this.intakeState = intakeState;
		this.shooterState = shooterState;
		this.stateString = stateString;
		this.ledState = ledState;
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

	public LEDStates getLedStates() {
		return ledState;
	}

	public String getStateString() {
		return stateString;
	}
}
