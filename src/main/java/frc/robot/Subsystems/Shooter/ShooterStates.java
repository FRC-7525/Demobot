package frc.robot.Subsystems.Shooter;

import static frc.robot.Subsystems.Shooter.ShooterConstants.*;

public enum ShooterStates {
	IDLE(IDLE_RPS),
	FIXEDSHOOT(FIXED_SHOOT_RPS),
	DYNAMICSHOOT(FIXED_SHOOT_RPS),
	LONGPASS(LONG_PASS_RPS);

	private String stateString;
	private double shooterSpeed;

	ShooterStates(double shooterSpeed) {
		this.stateString = this.name();
		this.shooterSpeed = shooterSpeed;
	}

	public String getStateString() {
		return stateString;
	}

	public Double getShooterRPS() {
		return shooterSpeed;
	}
}
