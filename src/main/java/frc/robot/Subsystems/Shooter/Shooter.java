package frc.robot.Subsystems.Shooter;

import static frc.robot.Subsystems.Shooter.ShooterConstants.*;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {

	private static Shooter instance;
	protected ShooterStates state;
	protected SparkMax followerleftMotor;
	protected SparkMax leaderrightMotor;
	protected SparkMax passMotor;
	protected PIDController motorcontrollerright;
	private SparkMaxConfig followerConfig;
	protected SimpleMotorFeedforward feedforward;

	// makes sure that there is only one instance of the Shooter class, and if there isn't, it creates a new one (this is a singleton pattern)
	public static Shooter getInstance() {
		if (instance == null) {
			instance = new Shooter();
		}

		return instance;
	}

	public Shooter() {
		state = ShooterStates.IDLE;

		motorcontrollerright = new PIDController(KP, KI, KD);
		feedforward = new SimpleMotorFeedforward(0.26, 0.00207, 0);

		followerleftMotor = new SparkMax(LEFT_MOTOR_ID, MotorType.kBrushless);
		leaderrightMotor = new SparkMax(RIGHT_MOTOR_ID, MotorType.kBrushless);
		passMotor = new SparkMax(PASS_MOTOR_ID, MotorType.kBrushless);

		// this makes the left motor follow the right motor, and inverts it so that they spin in opposite directions
		followerConfig = new SparkMaxConfig();
		followerConfig.follow(leaderrightMotor, true);
		followerleftMotor.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

        // configures initial settings for the motors, such as idle mode is set to coast
		leaderrightMotor.configure(new SparkMaxConfig().idleMode(IdleMode.kCoast), ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
		followerleftMotor.configure(new SparkMaxConfig().idleMode(IdleMode.kCoast), ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
		passMotor.configure(new SparkMaxConfig().idleMode(IdleMode.kCoast), ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
	}

	public void setState(ShooterStates state) {
		this.state = state;
	}

	public boolean atSpeed() {
		// checks if the shooter is at the target speed by comparing the current velocity of the follower left motor's encoder to the target speed in RPM, allowing a tolerance of 60 RPM
		return Math.abs(followerleftMotor.getEncoder().getVelocity() - (state.getShooterRPS().in(Units.RotationsPerSecond) * RPS_TO_RPM_CONVERSION_FACTOR)) < 60;
	}

	public void periodic() {
		// logging
		SmartDashboard.putNumber("Shooter/Shooter RPM", followerleftMotor.getEncoder().getVelocity());
		SmartDashboard.putNumber("Shooter/Target Speed (RPM)", state.getShooterRPS().in(Units.RotationsPerSecond) * RPS_TO_RPM_CONVERSION_FACTOR);
		SmartDashboard.putNumber("Passthrough/Pass RPM", passMotor.getEncoder().getVelocity());
		SmartDashboard.putData("Shooter/PID Controller", motorcontrollerright);
		
		// Check if Sparkmaxes are connected to CANBus
		SmartDashboard.putBoolean("ShooterSpark13", followerleftMotor.getLastError() == com.revrobotics.REVLibError.kOk);
		SmartDashboard.putBoolean("ShooterSpark12", leaderrightMotor.getLastError() == com.revrobotics.REVLibError.kOk);
		SmartDashboard.putBoolean("PassthroughSpark14", passMotor.getLastError() == com.revrobotics.REVLibError.kOk);

		// What ever is on the SmartDashboard (Elastic) will be used to set the feedforward values, and then the values will be put back onto the SmartDashboard for logging
		feedforward.setKa(SmartDashboard.getNumber("kA", feedforward.getKa()));
		SmartDashboard.putNumber("kA", feedforward.getKa());
		feedforward.setKv(SmartDashboard.getNumber("kV", feedforward.getKv()));
		SmartDashboard.putNumber("kV", feedforward.getKv());
		feedforward.setKs(SmartDashboard.getNumber("kS", feedforward.getKs()));
		SmartDashboard.putNumber("kS", feedforward.getKs());

        // States change speed of motors
		if (state == ShooterStates.IDLE) {
			leaderrightMotor.set(0);
			passMotor.set(0);
		} else if (state == ShooterStates.MIDSHOOT || state == ShooterStates.LOWSHOOT || state == ShooterStates.HIGHSHOOT){
			leaderrightMotor.setVoltage(
				motorcontrollerright.calculate(followerleftMotor.getEncoder().getVelocity(), state.getShooterRPS().in(Units.RotationsPerSecond) * RPS_TO_RPM_CONVERSION_FACTOR) + feedforward.calculate(state.getShooterRPS().in(Units.RotationsPerSecond) * RPS_TO_RPM_CONVERSION_FACTOR)
			);
			passMotor.set(PASS_SPEED);
			SmartDashboard.putBoolean("Shooter/On", true);
		} else {
			// for safety, if the state is not recognized, stop the motors
			leaderrightMotor.set(0);
			passMotor.set(0);
		}
	}
}
