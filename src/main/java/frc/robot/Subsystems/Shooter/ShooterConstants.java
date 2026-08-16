package frc.robot.Subsystems.Shooter;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class ShooterConstants {

	public static final AngularVelocity IDLE_RPS = Units.RotationsPerSecond.of(0);
	public static final AngularVelocity MID_SHOOT_RPS = Units.RotationsPerSecond.of(35); //org: 73
	public static final AngularVelocity LOW_SHOOT_RPS = Units.RotationsPerSecond.of(15);
	public static final AngularVelocity HIGH_SHOOT_RPS = Units.RotationsPerSecond.of(55);

	public static final int LEFT_MOTOR_ID = 13;
	public static final int RIGHT_MOTOR_ID = 12;

	public static final double KP = SmartDashboard.getNumber("KP", 0.0);
	public static final double KI = SmartDashboard.getNumber("KI", 0.0);
	public static final double KD = SmartDashboard.getNumber("KD", 0.0);

	public static final int RPS_TO_RPM_CONVERSION_FACTOR = 60;

}
