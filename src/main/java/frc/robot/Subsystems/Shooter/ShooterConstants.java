package frc.robot.Subsystems.Shooter;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.AngularVelocity;

public final class ShooterConstants {

	public static final AngularVelocity IDLE_RPS = Units.RotationsPerSecond.of(0);
	public static final AngularVelocity WINDUP_RPS = Units.RotationsPerSecond.of(0.5);
	public static final AngularVelocity MID_SHOOT_RPS = Units.RotationsPerSecond.of(35); //org: 73
	public static final AngularVelocity LOW_SHOOT_RPS = Units.RotationsPerSecond.of(15);
	public static final AngularVelocity HIGH_SHOOT_RPS = Units.RotationsPerSecond.of(55);

	public static final int LEFT_MOTOR_ID = 13;
	public static final int RIGHT_MOTOR_ID = 11;
	public static final int PASS_MOTOR_ID = 12;

	public static final double KP = 0.0;
	public static final double KI = 0.0;
	public static final double KD = 0.0;

	public static final double KS = 0.26;
	public static final double KV = 0.00207;
	public static final double KA = 0.0;

	public static final int PASS_SPEED = 1;

	public static final int RPS_TO_RPM_CONVERSION_FACTOR = 60;
	public static final int TOLERANCE = 60; // RPM

	public static final double PASSTHROUGH_INTERVAL = 1; // seconds;
}
