package frc.robot.Subsystems.Drive;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;

public class DriveConstants {

	public static final double MAX_SPEED = 4.6;
	public static final double MANUAL_SCALE = 0.5;
	public static final double DEMO_SCALE = 0.2;
	public static final double SLOW_SCALE = 0.33;
	public static final Transform2d RED_TRANSFORM = new Transform2d(Translation2d.kZero, Rotation2d.fromDegrees(180));
}
