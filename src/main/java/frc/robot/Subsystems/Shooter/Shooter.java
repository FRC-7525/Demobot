package frc.robot.Subsystems.Shooter;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.units.Units;

import static frc.robot.Subsystems.Shooter.ShooterConstants.*;

public class Shooter {
    protected ShooterStates state;
    protected SparkMax followerleftMotor; 
    protected SparkMax leaderrightMotor;
    protected PIDController motorcontrollerright;
    private SparkMaxConfig followerConfig;
    protected SimpleMotorFeedforward feedforward;

    public Shooter() {
        state = ShooterStates.IDLE;
        motorcontrollerright = WHEEL_PID.get();
        followerleftMotor = new SparkMax(LEFT_MOTOR_ID, MotorType.kBrushless);
        leaderrightMotor = new SparkMax(RIGHT_MOTOR_ID, MotorType.kBrushless);
        followerConfig = new SparkMaxConfig();
        followerConfig.follow(leaderrightMotor, true);
        followerleftMotor.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        feedforward = WHEEL_FEEDFORWARD.get();
    }
    
    public void setState(ShooterStates state) {
        this.state = state;
    }


    public void periodic() {
        if (state == ShooterStates.IDLE) {
            leaderrightMotor.set(IDLE_SPEED_OR_VOLTAGE);
        } else {
            leaderrightMotor.set(motorcontrollerright.calculate(leaderrightMotor.getEncoder().getVelocity(), state.getShooterRPS().in(Units.RadiansPerSecond) * RPS_TO_RPM_CONVERSION_FACTOR) + feedforward.calculate(state.getShooterRPS().in(Units.RadiansPerSecond) * RPS_TO_RPM_CONVERSION_FACTOR));
        }
    }
}

