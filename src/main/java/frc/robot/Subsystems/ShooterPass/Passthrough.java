package frc.robot.Subsystems.ShooterPass;

import static frc.robot.Subsystems.ShooterPass.PassthroughConstants.*;
import static frc.robot.Subsystems.ShooterPass.PassthroughStates.*;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Passthrough {

	private static Passthrough instance;

	protected PassthroughStates state;
	protected SparkMax mainmotor;

	public static Passthrough getInstance() {
		if (instance == null) {
			instance = new Passthrough();
		}

		return instance;
	}

	public Passthrough() {
		state = IDLE;
		mainmotor = new SparkMax(MAIN_MOTOR_ID, MotorType.kBrushless);
		mainmotor.configure(new SparkMaxConfig().idleMode(IdleMode.kCoast), ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
	}

	public void setState(PassthroughStates state) {
		this.state = state;
	}

	public void periodic() {
		SmartDashboard.putNumber("Passthrough/Pass RPM", mainmotor.getEncoder().getVelocity());

		// Check if Sparkmax is connected to CANBus
		SmartDashboard.putBoolean("PassthroughSpark14", mainmotor.getLastError() == com.revrobotics.REVLibError.kOk);


		if (state == IDLE) {
			mainmotor.set(0);
		} else if (state == PASS) {
			mainmotor.set(SPEED);
			SmartDashboard.putBoolean("Passthrough/On", true);
		} else {
			mainmotor.set(0);
		}
	}
}
