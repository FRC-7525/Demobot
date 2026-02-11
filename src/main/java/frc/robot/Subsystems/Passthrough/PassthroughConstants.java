package frc.robot.Subsystems.Passthrough;

import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecondPerSecond;

import edu.wpi.first.units.measure.AngularVelocity;

public class PassthroughConstants {

        public static final double PASSTHROUGHMAINMOTOR_RPS = 40;
        public static final double PASSTHROUGHBACKMOTOR_RPS = 60;

        public static final double MOTOR_PROPORTION = 0.0002;
        public static final double MOTOR_INTEGRAL = 0;
        public static final double MOTOR_DERIVATIVE = 0;
        public static final int MAIN_MOTOR_ID = 1;
        public static final int BACK_MOTOR_ID = 2;


        public static final AngularVelocity IDLE_SPEED = RotationsPerSecond.of(0);
        public static final AngularVelocity PASS_SPEED = RotationsPerSecond.of(15);


}

