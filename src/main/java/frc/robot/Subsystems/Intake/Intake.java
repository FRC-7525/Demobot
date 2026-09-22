package frc.robot.Subsystems.Intake;

import static edu.wpi.first.units.Units.Degrees;
import static frc.robot.GlobalConstants.ROBOT_MODE;
import static frc.robot.Subsystems.Intake.IntakeConstants.*;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.GlobalConstants.RobotMode;

public class Intake {

	private static Intake instance;
	private final SparkMax wheelMotor;
	private final SparkMax armMotor;

	private IntakeStates currentState;

	private PIDController armPIDController;

	private boolean agitatingHigh;
	private final Timer agitateTimer;

	public Intake() {
		wheelMotor = new SparkMax(IntakeConstants.WHEEL_MOTOR_ID, MotorType.kBrushless);
		armMotor = new SparkMax(IntakeConstants.ARM_MOTOR_ID, MotorType.kBrushless);

		SparkMaxConfig config = new SparkMaxConfig();
		config.idleMode(IdleMode.kBrake);
		armMotor.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
		currentState = IntakeStates.IN_IDLE;
		agitatingHigh = false;
		agitateTimer = new Timer();

		armPIDController = new PIDController(ARM_P, ARM_I, ARM_D);
		armMotor.getEncoder().setPosition(0);
	}

	// Makes sure that there is only ONE instance of the Intake class, and if there isn't, it creates a new one (this is a singleton pattern)
	public static Intake getInstance() {
		if (instance == null) {
			instance = new Intake();
		}

		return instance;
	}

	public void setState(IntakeStates state) {
		if (state == currentState) return;
		currentState = state;

		if (state == IntakeStates.AGITATE) {
			agitatingHigh = false;
			agitateTimer.restart();
		} else {
			agitateTimer.stop();
		}
	}

	public IntakeStates getState() {
		return currentState;
	}

	public void periodic() {
		double armSetpointDeg;

		if (currentState == IntakeStates.AGITATE) {
			if (agitateTimer.hasElapsed(AGITATE_INTERVAL)) {
				agitatingHigh = !agitatingHigh;
				agitateTimer.restart();
			}
			armSetpointDeg = (agitatingHigh ? ARM_ANGLE_AGITATE_HIGH : ARM_ANGLE_AGITATE_LOW).in(Degrees);
		} else {
			armSetpointDeg = currentState.getArmAngle().in(Degrees);
		}

		double armPosition = Units.rotationsToDegrees(armMotor.getEncoder().getPosition());
		double armOutput = armPIDController.calculate(armPosition, armSetpointDeg);

		armMotor.set(armOutput);
		wheelMotor.set(currentState.getWheelSpeed());

		log(armPosition, armSetpointDeg, armOutput);
	}

	private void log(double armPosition, double armSetpoint, double armOutput) {
		SmartDashboard.putString("Intake/State", currentState.toString());

		// Arm
		SmartDashboard.putNumber("Intake/Arm/Position", armPosition);
		SmartDashboard.putNumber("Intake/Arm/Setpoint", armSetpoint);
		SmartDashboard.putNumber("Intake/Arm/Error", armSetpoint - armPosition);
		SmartDashboard.putNumber("Intake/Arm/Output", armOutput);

		// Roller
		SmartDashboard.putNumber("Intake/Wheel/Commanded", currentState.getWheelSpeed());
		SmartDashboard.putNumber("Intake/Wheel/VelocityRPM", wheelMotor.getEncoder().getVelocity());

		// Agitation
		SmartDashboard.putBoolean("Intake/Agitate/AtHigh", agitatingHigh);
		SmartDashboard.putNumber("Intake/Agitate/Timer", agitateTimer.get());
		if (ROBOT_MODE == RobotMode.TUNE) {
			SmartDashboard.putData(armPIDController);
		}
	}
}
