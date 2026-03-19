package frc.robot.Subsystems.Climber;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber {

	private static Climber instance;
	protected ClimberStates state;
	protected SparkMax motor;
	private double driverRequestSpeed;

	public static Climber getInstance() {
		if (instance == null) {
			instance = new Climber();
		}

		return instance;
	}

	public Climber() {
		state = ClimberStates.IDLE;
		driverRequestSpeed = 0;
		motor = new SparkMax(11, MotorType.kBrushless);
	}

	public void setState(ClimberStates state) {
		this.state = state;
	}
	public void setSpeed(double speed) {
		driverRequestSpeed = speed;
	}

	public void periodic() {
		if (state == ClimberStates.IDLE) {
			motor.set(0);
		} else {
			motor.set(driverRequestSpeed);
		}

		SmartDashboard.putNumber("Climber DEG", motor.getAbsoluteEncoder().getPosition());
	}
}
