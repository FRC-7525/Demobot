package frc.robot.Subsystems.Intake;


import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static frc.robot.Subsystems.Intake.IntakeConstants.*;
import edu.wpi.first.math.controller.PIDController;



public class Intake{
    
    private final SparkMax wheelMotor;
    private final SparkMax armMotor;
    
    private IntakeStates currentState;

    private PIDController armPIDController;
    private PIDController wheelPIDController;

    public Intake() {
        wheelMotor = new SparkMax(IntakeConstants.WHEEL_MOTOR_ID, MotorType.kBrushless);
        armMotor = new SparkMax(IntakeConstants.ARM_MOTOR_ID, MotorType.kBrushless);

        currentState = IntakeStates.IDLE;

        armPIDController = new PIDController(ARM_P, ARM_I, ARM_D);
        wheelPIDController = new PIDController(WHEEL_P, WHEEL_I, WHEEL_D);
    }

    public void setState(IntakeStates state) {
        currentState = state;
    }

    public IntakeStates getState() {
        return currentState;
    }

    public void periodic() {
        armMotor.set(armPIDController.calculate(armMotor.getEncoder().getPosition(), currentState.getArmAngle().in(Degrees)));
        wheelMotor.set(wheelPIDController.calculate(wheelMotor.getEncoder().getVelocity(), currentState.getWheelSpeed().in(RotationsPerSecond)));
    }
}