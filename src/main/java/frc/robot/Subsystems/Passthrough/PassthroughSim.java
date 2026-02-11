package frc.robot.Subsystems.Passthrough;
import com.revrobotics.sim.SparkMaxSim;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PassthroughSim extends Passthrough {
    private SparkMaxSim mainmotorSim;
    private SparkMaxSim backmotorSim;
    private FlywheelSim mainWheel;
    private FlywheelSim backWheel;

    public PassthroughSim() {
        mainmotorSim = new SparkMaxSim(mainmotor, DCMotor.getNEO(1));
        backmotorSim = new SparkMaxSim(backmotor, DCMotor.getNEO(1));
        mainWheel = new FlywheelSim(
            LinearSystemId.createFlywheelSystem(
                DCMotor.getNEO(1), 0.0001, 1),
                DCMotor.getNEO(1));
        backWheel = new FlywheelSim(
            LinearSystemId.createFlywheelSystem(
                DCMotor.getNEO(1), 0.0001, 1),
                DCMotor.getNEO(1));
    }

    public void setState(PassthroughStates state) {
        this.state = state;
    }

    public void periodic() {
        // Rand sim annoying stuffs
        backmotorSim.setVelocity(Units.radiansToRotations(backWheel.getAngularVelocityRadPerSec()));
        mainmotorSim.setVelocity(Units.radiansToRotations(mainWheel.getAngularVelocityRadPerSec()));
        backmotorSim.setBusVoltage(12);
        mainmotorSim.setBusVoltage(12);
        mainWheel.update(0.02);
        backWheel.update(0.02);

        // Logging

        SmartDashboard.putNumber("Passthrough/Current MainSpeed (RPM)", mainWheel.getAngularVelocityRPM());
        SmartDashboard.putNumber("Passthrough/Target MainSpeed (RPM)", PassthroughConstants.PASSTHROUGHMAINMOTOR_RPS * 60);
        SmartDashboard.putData("Passthrough/PID Controller main", mainmotorcontroller);

        SmartDashboard.putNumber("Passthrough/Current BackSpeed (RPM)", backWheel.getAngularVelocityRPM());
        SmartDashboard.putNumber("Passthrough/Target BackSpeed (RPM)", PassthroughConstants.PASSTHROUGHBACKMOTOR_RPS * 60);
        SmartDashboard.putData("Passthrough/PID Controller back", backmotorcontroller);

        if (state == PassthroughStates.IDLE) {
            mainWheel.setInputVoltage(0);
            backWheel.setInputVoltage(0);
        } else if (state == PassthroughStates.PASS){
            mainWheel.setInputVoltage(12 * mainmotorcontroller.calculate(mainWheel.getAngularVelocityRPM(), PassthroughConstants.PASSTHROUGHMAINMOTOR_RPS * 60));
            backWheel.setInputVoltage(12 * backmotorcontroller.calculate(backWheel.getAngularVelocityRPM(), PassthroughConstants.PASSTHROUGHBACKMOTOR_RPS * 60));

        }
    }

    }
    
