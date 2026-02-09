package frc.robot.Subsystems.Shooter;

import com.revrobotics.sim.SparkMaxSim;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterSim extends Shooter {
    private SparkMaxSim leftSim;
    private SparkMaxSim rightSim;
    private FlywheelSim bigWheel;

    public ShooterSim() {
        leftSim = new SparkMaxSim(followerleftMotor, DCMotor.getNEO(1));
        rightSim = new SparkMaxSim(leaderrightMotor, DCMotor.getNEO(1));
        bigWheel = new FlywheelSim(
            LinearSystemId.createFlywheelSystem(
                DCMotor.getNEO(2),
                0.001,
                1
            ),
            DCMotor.getNEO(2));
    }

    public void setState(ShooterStates state) {
        this.state = state;
    }

    public void periodic() {
        // Rand sim annoying stuffs
        leftSim.setVelocity(Units.radiansToRotations(bigWheel.getAngularVelocityRadPerSec()));
        rightSim.setVelocity(Units.radiansToRotations(bigWheel.getAngularVelocityRadPerSec()));
        leftSim.setBusVoltage(12);
        rightSim.setBusVoltage(12);
        bigWheel.update(0.02);

        // Logging
        SmartDashboard.putNumber("Shooter/Current Speed (RPM)", bigWheel.getAngularVelocityRPM());
        SmartDashboard.putNumber("Shooter/Target Speed (RPM)", state.getShooterRPS() * 60);
        SmartDashboard.putData("Shooter/PID Controller", motorcontrollerright);

        feedforward.setKa(SmartDashboard.getNumber("kA", 0));
        SmartDashboard.putNumber("kA", feedforward.getKa());
        feedforward.setKv(SmartDashboard.getNumber("kV", 0.001));
        SmartDashboard.putNumber("kV", feedforward.getKv());
        feedforward.setKs(SmartDashboard.getNumber("kS", 0.1));
        SmartDashboard.putNumber("kS", feedforward.getKs());



        if (state == ShooterStates.IDLE) {
            bigWheel.setInputVoltage(0);
        } else {
            bigWheel.setInputVoltage(12 * motorcontrollerright.calculate(bigWheel.getAngularVelocityRPM(), state.getShooterRPS() * 60) + feedforward.calculate(state.getShooterRPS() * 60));
        }
    }

}
