package frc.robot.Subsystems.Intake;


import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import static edu.wpi.first.units.Units.Degrees;
import static frc.robot.Subsystems.Intake.IntakeConstants.*;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Intake{
    
    private final SparkMax wheelMotor;
    private final SparkMax armMotor;
    
    private IntakeStates currentState;

    private PIDController armPIDController;

    private boolean agitatingHigh;
    private final Timer agitateTimer;


    public Intake() {
        wheelMotor = new SparkMax(IntakeConstants.WHEEL_MOTOR_ID, MotorType.kBrushless);
        armMotor = new SparkMax(IntakeConstants.ARM_MOTOR_ID, MotorType.kBrushless);

        currentState = IntakeStates.IDLE;
        agitatingHigh = false;
        agitateTimer = new Timer();

        armPIDController = new PIDController(ARM_P, ARM_I, ARM_D);
    }

    public void setState(IntakeStates state) {
       if (state == currentState) return;
            currentState = state;

        if (state == IntakeStates.AGITATE) {
            agitatingHigh = false;
            agitateTimer.restart();
        } 
        else {    
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
        armSetpointDeg = (agitatingHigh ? ARM_ANGLE_AGITATE_HIGH
                                        : ARM_ANGLE_AGITATE_LOW).in(Degrees);
    } else {
        armSetpointDeg = currentState.getArmAngle().in(Degrees);
    }

    double armPosition = armMotor.getEncoder().getPosition();
    double armOutput = armPIDController.calculate(armPosition, armSetpointDeg);

    armMotor.set(armOutput);
    wheelMotor.set(currentState.getWheelSpeed());

    log(armPosition, armSetpointDeg, armOutput);
}


    private void log(double armPosition, double armSetpoint, double armOutput) {
    SmartDashboard.putString("Intake/State", currentState.toString());

    // arm
    SmartDashboard.putNumber("Intake/Arm/Position", armPosition);
    SmartDashboard.putNumber("Intake/Arm/Setpoint", armSetpoint);
    SmartDashboard.putNumber("Intake/Arm/Error", armSetpoint - armPosition);
    SmartDashboard.putNumber("Intake/Arm/Output", armOutput);


    // wheel
    SmartDashboard.putNumber("Intake/Wheel/Commanded", currentState.getWheelSpeed());
    SmartDashboard.putNumber("Intake/Wheel/VelocityRPM", wheelMotor.getEncoder().getVelocity());


    // agitates
    SmartDashboard.putBoolean("Intake/Agitate/AtHigh", agitatingHigh);
    SmartDashboard.putNumber("Intake/Agitate/Timer", agitateTimer.get());
}
}