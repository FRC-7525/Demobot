package frc.robot.Subsystems.Shooter;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;



public class Shooter {
    private ShooterStates state;  
    private TalonFX leftMotor; 
    private TalonFX rightMotor;
    private PIDController leadermotorcontrollerleft;
    private PIDController followermotorcontrollerright;

    public Shooter() {
        state = ShooterStates.IDLE;
        motorcontrollerright = new PIDController(Constants.Shooter.MOTOR_RIGHT_PROPORTION, Constants.Shooter.MOTOR_RIGHT_INTEGRAL, Constants.Shooter.MOTOR_RIGHT_DERIVATIVE); //PID Tune values
        
        followerleftMotor = new TalonFX(Constants.Shooter.LEFT_MOTOR_ID);
        leaderrightMotor = new TalonFX(Constants.Shooter.RIGHT_MOTOR_ID);

        followerleftMotor.set(ControlMode.Follower, leaderrightMotor.getDeviceID());
        followerleftMotor.setInverted(TalonFXInvertType.OpposeMaster); // oppose or follow master??? there is a .FollowMaster Method
    }
    
    public void setState(ShooterStates state) {
        this.state = state;
    }

    public void periodic() {
        if (state == IDLE) {
            rightmotor.set(0);
        } else {
            rightMotor.set(motorcontrollerright.calculate(rightMotor.getVelocity().getValueAsDouble(), state.getShooterRPS()));
        }
    }
}

