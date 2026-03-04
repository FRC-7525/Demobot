package frc.robot.Subsystems.Intake;


import static frc.robot.Subsystems.Intake.IntakeConstants.*;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public class IntakeIOReal implements IntakeIO {

    private SparkMax pivotMotor;
    private SparkMax wheelMotor;
    private PIDController pivotController;
    private PIDController wheelSpeedController;

    public IntakeIOReal() {
        this.pivotMotor = new SparkMax(PIVOT_MOTOR_ID, MotorType.kBrushless);
        this.wheelMotor = new SparkMax(WHEEL_MOTOR_ID, MotorType.kBrushless);
        this.pivotController = IntakeConstants.PIVOT_CONTROLLER.get();
        this.wheelSpeedController = IntakeConstants.WHEEL_CONTROLLER.get();

    }

    @Override
    public void setWheelSpeed(AngularVelocity wheelSpeed) {
        wheelMotor.setVoltage(wheelSpeedController.calculate(getCurrentWheelSpeed().in(RotationsPerSecond), wheelSpeed.in(RotationsPerSecond)));
    }

    @Override
    public void setTargetAngle(Angle targetAngle) {
        pivotMotor.setVoltage(pivotController.calculate(getCurrentAngle().in(Degrees), targetAngle.in(Degrees)));
    }

    @Override
    public Angle getCurrentAngle() {
        return Rotations.of(pivotMotor.getAbsoluteEncoder().getPosition());
    }

    @Override
    public AngularVelocity getCurrentWheelSpeed() {
       return RotationsPerSecond.of(wheelMotor.getAbsoluteEncoder().getVelocity() / 60);
    }

    @Override
    public double getWheelMotorCurrent() {
        return wheelMotor.getOutputCurrent();
    }

    @Override
    public double getPivotMotorCurrent() {
        return pivotMotor.getOutputCurrent();

    }
    
}
