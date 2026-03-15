package frc.robot.Subsystems.Climber;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import static frc.robot.Subsystems.Climber.ClimberConstants.COMINGIN_SPEED;
import static frc.robot.Subsystems.Climber.ClimberConstants.GOINGOUT_SPEED;
import static frc.robot.Subsystems.Climber.ClimberConstants.IDLE_SPEED;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber {

	private static Climber instance;
	protected ClimberStates state;
	protected SparkMax motor;

	public static Climber getInstance() {
		if (instance == null) {
			instance = new Climber();
		}

		return instance;
	}

	public Climber() {
		state = ClimberStates.IDLE;
		motor = new SparkMax(11, MotorType.kBrushless);
	}

	public void setState(ClimberStates state) {
		this.state = state;
	}

	public void periodic() {
		if (state == ClimberStates.IDLE) {
			motor.set(IDLE_SPEED);
		} else if(state ==ClimberStates.GOINGOUT) {
			motor.set(GOINGOUT_SPEED);
		}
		else if (state == ClimberStates.COMINGIN) {
			motor.set(COMINGIN_SPEED);
		}

		SmartDashboard.putNumber("Climber rot", motor.getAbsoluteEncoder().getPosition());
	}
}
