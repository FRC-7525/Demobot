package frc.robot.Subsystems.Shooter;

import static frc.robot.Subsystems.Shooter.ShooterConstants.*;

public enum ShooterStates {
	IDLE(IDLE_SPEED),
	MIDSHOOT(MID_SHOOT_SPEED),
	LOWSHOOT(LOW_SHOOT_SPEED),
	HIGHSHOOT(HIGH_SHOOT_SPEED);

	private String stateString;
	private double shooterSpeed;

	ShooterStates(double shooterSpeed) {
		this.stateString = this.name();
		this.shooterSpeed = shooterSpeed;
	}

	public String getStateString() {
		return stateString;
	}

	public double getShooterRPS() {
		return shooterSpeed;
	}
}
