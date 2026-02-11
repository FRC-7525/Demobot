package frc.robot.Subsystems.Shooter;

import static frc.robot.Subsystems.Shooter.ShooterConstants.*;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class Shooter {

	protected ShooterStates state;
	protected SparkMax followerleftMotor;
	protected SparkMax leaderrightMotor;
	protected PIDController motorcontrollerright;
	private SparkMaxConfig followerConfig;
	protected SimpleMotorFeedforward feedforward;

	public Shooter() {
		state = ShooterStates.IDLE;
		motorcontrollerright = new PIDController(MOTOR_RIGHT_PROPORTION, MOTOR_RIGHT_INTEGRAL, MOTOR_RIGHT_DERIVATIVE); //PID Tune values
		followerleftMotor = new SparkMax(LEFT_MOTOR_ID, MotorType.kBrushless);
		leaderrightMotor = new SparkMax(RIGHT_MOTOR_ID, MotorType.kBrushless);
		followerConfig = new SparkMaxConfig();
		followerConfig.follow(leaderrightMotor, true);
		followerleftMotor.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
		feedforward = new SimpleMotorFeedforward(kS, kV, kA);
	}

	public void setState(ShooterStates state) {
		this.state = state;
	}

	public void periodic() {
		if (state == ShooterStates.IDLE) {
			leaderrightMotor.set(IDLESpeedOrVoltage);
		} else {
			leaderrightMotor.set(motorcontrollerright.calculate(leaderrightMotor.getEncoder().getVelocity(), state.getShooterRPS() * RPStoRPMConversionFactor) + feedforward.calculate(state.getShooterRPS() * RPStoRPMConversionFactor));
		}
	}
}
