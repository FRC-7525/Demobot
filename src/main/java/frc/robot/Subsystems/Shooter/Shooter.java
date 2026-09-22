package frc.robot.Subsystems.Shooter;

import static frc.robot.GlobalConstants.ROBOT_MODE;
import static frc.robot.Subsystems.Shooter.ShooterConstants.*;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.GlobalConstants.RobotMode;

public class Shooter {

	private static Shooter instance;
	protected ShooterStates state;
	protected SparkMax followerleftMotor;
	protected SparkMax leaderrightMotor;
	protected SparkMax passMotor;
	protected PIDController motorcontrollerright;
	private SparkMaxConfig followerConfig;
	protected SimpleMotorFeedforward feedforward;
	private final Timer passthroughTimer;
	private boolean ready;
	private boolean wasIdle;

	// Makes sure that there is only ONE instance of the Shooter class, and if there isn't, it creates a new one (this is a singleton pattern)
	public static Shooter getInstance() {
		if (instance == null) {
			instance = new Shooter();
		}

		return instance;
	}

	public Shooter() {
		state = ShooterStates.IDLE;

		followerleftMotor = new SparkMax(LEFT_MOTOR_ID, MotorType.kBrushless);
		leaderrightMotor = new SparkMax(RIGHT_MOTOR_ID, MotorType.kBrushless);
		passMotor = new SparkMax(PASS_MOTOR_ID, MotorType.kBrushless);

		// This makes the left motor follow the right motor, and inverts it so that they spin in opposite directions
		followerConfig = new SparkMaxConfig();
		followerConfig.follow(leaderrightMotor, true);
		followerConfig.idleMode(IdleMode.kBrake);
		followerConfig.smartCurrentLimit(STALL_LIMIT, FREE_LIMIT);
		followerleftMotor.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

		// Configures initial settings for the motors, such as idle mode is set to coast
		leaderrightMotor.configure(new SparkMaxConfig().idleMode(IdleMode.kCoast).smartCurrentLimit(STALL_LIMIT, FREE_LIMIT), ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
		followerleftMotor.configure(new SparkMaxConfig().idleMode(IdleMode.kCoast).smartCurrentLimit(STALL_LIMIT, FREE_LIMIT), ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
		passMotor.configure(new SparkMaxConfig().idleMode(IdleMode.kCoast).smartCurrentLimit(STALL_LIMIT, FREE_LIMIT), ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
		passthroughTimer = new Timer();
	}

	public void setState(ShooterStates state) {
		this.state = state;
	}

	public boolean atSpeed() {
		//return Math.abs(followerleftMotor.getEncoder().getVelocity() - (state.getShooterRPS().in(Units.RotationsPerSecond) * RPS_TO_RPM_CONVERSION_FACTOR)) < TOLERANCE;
		// checks if the shooter is at the target speed by comparing the current velocity of the follower left motor's encoder to the target speed in RPM, allowing a tolerance of 60 RPM
		return true;
	}

	public void periodic() {
		// Logging
		SmartDashboard.putNumber("Shooter/Shooter RPM", followerleftMotor.getEncoder().getVelocity());
		SmartDashboard.putNumber("Shooter/Target Speed", state.getShooterRPS());
		SmartDashboard.putNumber("Shooter/Pass RPM", passMotor.getEncoder().getVelocity());
		SmartDashboard.putData("Shooter/PID Controller", motorcontrollerright);

		// Check if Sparkmaxes are connected to CANBus
		SmartDashboard.putBoolean("ShooterSpark13", followerleftMotor.getLastError() == com.revrobotics.REVLibError.kOk);
		SmartDashboard.putBoolean("ShooterSpark12", leaderrightMotor.getLastError() == com.revrobotics.REVLibError.kOk);
		SmartDashboard.putBoolean("PassthroughSpark14", passMotor.getLastError() == com.revrobotics.REVLibError.kOk);

		if (ROBOT_MODE == RobotMode.TUNE) {
			// What ever is on the SmartDashboard (Elastic) will be used to set the feedforward values, and then the values will be put back onto the SmartDashboard for logging
			feedforward.setKa(SmartDashboard.getNumber("kA", feedforward.getKa()));
			SmartDashboard.putNumber("Shooter/kA", feedforward.getKa());
			feedforward.setKv(SmartDashboard.getNumber("kV", feedforward.getKv()));
			SmartDashboard.putNumber("Shooter/kV", feedforward.getKv());
			feedforward.setKs(SmartDashboard.getNumber("kS", feedforward.getKs()));
			SmartDashboard.putNumber("Shooter/kS", feedforward.getKs());
		}

		// States change speed of motors
		// Most horrible code ever written :(
		// Should really be done using states and transitions, but no team lib in this project
		if (state == ShooterStates.IDLE) {
			leaderrightMotor.set(0);
			passMotor.set(0);
			ready = false;
			wasIdle = true;
		} else if (state == ShooterStates.MIDSHOOT || state == ShooterStates.LOWSHOOT || state == ShooterStates.HIGHSHOOT) {
			if (!ready && wasIdle) {
				wasIdle = false;
				passthroughTimer.reset();
				passthroughTimer.start();
			} else if (!ready && !wasIdle) {
				leaderrightMotor.set(state.getShooterRPS());
				if (passthroughTimer.hasElapsed(WINDUP_TIME)) {
					ready = true;
				}
			} else if (ready) {
				passMotor.set(PASS_SPEED);
				SmartDashboard.putBoolean("BRUH", true);
				leaderrightMotor.set(state.getShooterRPS());
			}
		} else {
			// For safety, if the state is not recognized, stop the motors
			leaderrightMotor.set(0);
			passMotor.set(0);
		}
	}
}
