package frc.robot.Subsystems.Climber;

import static frc.robot.Subsystems.Climber.ClimberConstants.L1_POSITION;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import swervelib.parser.json.ControllerPropertiesJson;

public class Climber {
    private static Climber instance;
    private ClimberStates state;  
    private SparkMax motor;
    private PIDController motorcontroller;
    private XboxController controller;

    public static Climber getInstance() {
        if (instance == null) {
            instance = new Climber();
        }

        return instance;
    }

public Climber() {
        state = ClimberStates.IDLE;
        motorcontroller = new PIDController(ClimberConstants.MOTOR_PROPORTION, ClimberConstants.MOTOR_INTEGRAL, ClimberConstants.MOTOR_DERIVATIVE);
        controller = new XboxController(0);
        motor = new SparkMax(0, MotorType.kBrushless);
        
    }
 
    public void setState(ClimberStates state) {
        this.state = state;
    }

    public void periodic() {

        if (state == ClimberStates.IDLE) {
            motor.set(0);
        } else {
            motor.set(motorcontroller.calculate(motor.getEncoder().getPosition(), state.getPosition()));
        }
      
        
        if (controller.getYButtonPressed()) {
            state = ClimberStates.L1;
        }

        SmartDashboard.putNumber("Climber rot", motor.getAbsoluteEncoder().getPosition());
    }

}
