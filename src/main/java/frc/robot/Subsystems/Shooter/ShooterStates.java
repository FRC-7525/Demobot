package frc.robot.Subsystems.Shooter;

import static frc.robot.Subsystems.Shooter.ShooterConstants.*;

import edu.wpi.first.units.measure.AngularVelocity;

public enum ShooterStates {
	IDLE(IDLE_RPS),
	MIDSHOOT(MID_SHOOT_RPS),
	LOWSHOOT(LOW_SHOOT_RPS),
	HIGHSHOOT(HIGH_SHOOT_RPS);

	private String stateString;
	private AngularVelocity shooterSpeed;

	ShooterStates(AngularVelocity shooterSpeed) {
		this.stateString = this.name();
		this.shooterSpeed = shooterSpeed;
	}

	public String getStateString() {
		return stateString;
	}

	public AngularVelocity getShooterRPS() {
		return shooterSpeed;
	}
}
