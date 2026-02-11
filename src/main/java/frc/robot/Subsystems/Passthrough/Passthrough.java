package frc.robot.Subsystems.Passthrough;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.PIDController;


public class Passthrough {
        protected PassthroughStates state;
        protected SparkMax mainmotor;
        protected PIDController mainmotorcontroller;
        protected SparkMax backmotor;
        protected PIDController backmotorcontroller;
    
        public Passthrough() {
            state = PassthroughStates.IDLE;
            mainmotorcontroller = new PIDController(PassthroughConstants.MOTOR_PROPORTION, PassthroughConstants.MOTOR_INTEGRAL, PassthroughConstants.MOTOR_DERIVATIVE);
            mainmotor = new SparkMax(PassthroughConstants.MAIN_MOTOR_ID, MotorType.kBrushless);
            backmotorcontroller = new PIDController(PassthroughConstants.MOTOR_PROPORTION, PassthroughConstants.MOTOR_INTEGRAL, PassthroughConstants.MOTOR_DERIVATIVE);
            backmotor = new SparkMax(PassthroughConstants.BACK_MOTOR_ID, MotorType.kBrushless);
    }

    public void setState(PassthroughStates state) {
        this.state = state;
    }

    public void periodic() {
        if (state == PassthroughStates.IDLE) {
            mainmotor.set(0);
            backmotor.set(0);
        } else if (state == PassthroughStates.PASS) {
            mainmotor.set(mainmotorcontroller.calculate(mainmotor.getEncoder().getVelocity(), PassthroughConstants.PASSTHROUGHMAINMOTOR_RPS * 60));
            backmotor.set(backmotorcontroller.calculate(backmotor.getEncoder().getVelocity(), PassthroughConstants.PASSTHROUGHBACKMOTOR_RPS * 60));
        }
        }
    }

    