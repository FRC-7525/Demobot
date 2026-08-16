package frc.robot.Subsystems.Intake;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public class IntakeConstants {
    
    public static final AngularVelocity INTAKE_SPEED = RotationsPerSecond.of(1.0);
    public static final AngularVelocity OUTTAKE_SPEED = RotationsPerSecond.of(-1.0);
    public static final AngularVelocity IDLE_SPEED = RotationsPerSecond.of(0.0);
    public static final Angle ARM_ANGLE_INTAKE = Degrees.of(15.0);
    public static final Angle ARM_ANGLE_OUTTAKE = Degrees.of(15.0);
    public static final Angle ARM_ANGLE_IDLE = Degrees.of(90.0);


    public static final int WHEEL_MOTOR_ID = 1; //none of these are real numbers
    public static final int ARM_MOTOR_ID = 2;

    public static final double ARM_P = 0.1;
    public static final double ARM_I = 0.0;
    public static final double ARM_D = 0.0;

    public static final double WHEEL_P = 0.1;
    public static final double WHEEL_I = 0.0;       
    public static final double WHEEL_D = 0.0;
}
