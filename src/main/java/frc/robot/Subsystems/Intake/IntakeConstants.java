package frc.robot.Subsystems.Intake;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import frc.robot.GlobalConstants;

public class IntakeConstants {
	public static int PIVOT_MOTOR_ID = 1; // TODO: Change this to the correct can id
	public static int WHEEL_MOTOR_ID = 2;

	public static Angle IN_ANGLE = Degree.of(0);
	public static Angle OUT_ANGLE = Degree.of(45);
	public static AngularVelocity OFF_WHEEL_SPEED = RotationsPerSecond.of(0);
	public static AngularVelocity INTAKING_WHEEL_SPEED = RotationsPerSecond.of(20);

    public static final Supplier<PIDController> WHEEL_CONTROLLER = () ->
		switch (GlobalConstants.ROBOT_MODE) {
			case REAL -> new PIDController(0.1, 0, 0);
			case SIM -> new PIDController(0.0077, 0, 0.00013);
			case TESTING -> new PIDController(0.1, 0, 0);
		};

    public static final Supplier<PIDController> PIVOT_CONTROLLER = () ->
		switch (GlobalConstants.ROBOT_MODE) {
			case REAL -> new PIDController(0.1, 0, 0);
			case SIM -> new PIDController(0.0077, 0, 0.00013);
			case TESTING -> new PIDController(0.1, 0, 0);
		};
}
