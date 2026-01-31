package frc.robot;

import edu.wpi.first.math.util.Units;


public final class Constants {

    private Constants() {

    }

    public static final class Shooter {
        public static final double FIXED_SHOOT_RPS = 70;
        public static final int LEFT_MOTOR_ID = 15;
        public static final int RIGHT_MOTOR_ID = 14;
        public static final double LONG_PASS_RPS = 100;


        public static final double MOTOR_RIGHT_PROPORTION = 0.0002;
        public static final double MOTOR_RIGHT_INTEGRAL = 0;
        public static final double MOTOR_RIGHT_DERIVATIVE = 0;

        public static final double MOTOR_LEFT_PROPORTION = 0.0002;
        public static final double MOTOR_LEFT_INTEGRAL = 0;
        public static final double MOTOR_LEFT_DERIVATIVE = 0;
    }

}