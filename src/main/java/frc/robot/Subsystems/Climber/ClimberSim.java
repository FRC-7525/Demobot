package frc.robot.Subsystems.Climber;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Subsystems.Climber.ClimberConstants.*;

public class ClimberSim {
DCMotorSim climberSim; 
ClimberStates state;
private static ClimberSim instance;
PIDController Climbcontroller;
XboxController controller;

    public static ClimberSim getInstance() {
        if (instance == null) {
            instance = new ClimberSim();
        }

        return instance;
    }

    public ClimberSim() {
        state = ClimberStates.IDLE;
        climberSim = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getNEO(1), 1, 1), DCMotor.getNEO(1), 1);
        Climbcontroller = new PIDController( MOTOR_PROPORTION, MOTOR_INTEGRAL, MOTOR_DERIVATIVE);
        controller = new XboxController(0);
        
        
    }
        
    public void setState(ClimberStates state) {
        this.state = state;
    }

    public void periodic() {

        
        
        if (state == ClimberStates.IDLE) {
            climberSim.setInputVoltage(0);
        } else {
            climberSim.setInputVoltage(Climbcontroller.calculate(climberSim.getAngularPositionRad(), state.getPosition()));
        }
      
                
        if (controller.getYButtonPressed()) {
            state = ClimberStates.L1;
        }
        SmartDashboard.putNumber( "Climber position", climberSim.getAngularPositionRad());
    }



    
}
