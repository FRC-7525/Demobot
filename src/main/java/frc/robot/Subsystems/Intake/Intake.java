package frc.robot.Subsystems.Intake;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.GlobalConstants;

public class Intake {

    private static Intake instance; 

    private IntakeIO io;

    private IntakeStates currentState;

    private Intake(IntakeIO io) {
        this.io = io;

    }

	public static Intake getInstance() {
		if (instance == null) {
			switch (GlobalConstants.ROBOT_MODE) {
				case REAL -> instance = new Intake(new IntakeIOReal());
				case SIM -> instance = new Intake(new IntakeIOSim());
			}
		}
		return instance;
	}

    public void periodic() {
        io.setTargetAngle(currentState.getTargetAngle());
        io.setWheelSpeed(currentState.getWheelSpeed());
        logData();

    }

    public void setState(IntakeStates newState) {
        currentState = newState;
    }

    private void logData() {
        SmartDashboard.putString("Intake/CurrentState", currentState.getStateString());
        SmartDashboard.putNumber("Intake/targetSpeed (RPS)", currentState.getWheelSpeed().in(RotationsPerSecond));
        SmartDashboard.putNumber("Intake/targetAngle (DEG)", currentState.getTargetAngle().in(Degrees));
        SmartDashboard.putNumber("Intake/currentAngle (DEG)", io.getCurrentAngle().in(Degree));
        SmartDashboard.putNumber("Intake/currentWheelSpeed (RPS)", io.getCurrentWheelSpeed().in(RotationsPerSecond));
        SmartDashboard.putNumber("Intake/pivotMotorCurrent", io.getWheelMotorCurrent());
        SmartDashboard.putNumber("Intake/pivotMotorCurrent", io.getPivotMotorCurrent());
    }
}
