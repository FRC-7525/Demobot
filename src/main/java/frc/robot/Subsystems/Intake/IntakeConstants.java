package frc.robot.Subsystems.Intake;

import static edu.wpi.first.units.Units.Degrees;
import edu.wpi.first.units.measure.Angle;


public class IntakeConstants {
    
    public static final double INTAKE_SPEED = 0.9;
    public static final double OUTTAKE_SPEED = -0.9;
    public static final double IDLE_SPEED = 0.0;
    public static final Angle ARM_ANGLE_INTAKE = Degrees.of(15.0);
    public static final Angle ARM_ANGLE_OUTTAKE = Degrees.of(15.0);
    public static final Angle ARM_ANGLE_IDLE = Degrees.of(90.0);


    public static final int WHEEL_MOTOR_ID = 1; //none of these are real numbers
    public static final int ARM_MOTOR_ID = 2;

    public static final double ARM_P = 0.1;
    public static final double ARM_I = 0.0;
    public static final double ARM_D = 0.0;

    public static final Angle ARM_ANGLE_AGITATE_LOW  = Degrees.of(30.0);
    public static final Angle ARM_ANGLE_AGITATE_HIGH = Degrees.of(50.0);
    public static final double AGITATE_INTERVAL = 0.35;

}
