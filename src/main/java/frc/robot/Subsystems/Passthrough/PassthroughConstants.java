package frc.robot.Subsystems.Passthrough;

import static edu.wpi.first.units.Units.RotationsPerSecond;
import edu.wpi.first.units.measure.AngularVelocity;

public class PassthroughConstants {

	public static final double PASSTHROUGH_MAINMOTOR_RPS = 40;

	public static final int MAIN_MOTOR_ID = 14;

    public static final AngularVelocity IDLE_SPEED = RotationsPerSecond.of(0);
	public static final AngularVelocity PASS_SPEED = RotationsPerSecond.of(15);

	public static final int SPEED = 1;

	public static final double RPS_TO_RPM = 60;
}
