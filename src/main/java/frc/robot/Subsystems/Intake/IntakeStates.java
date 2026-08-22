package frc.robot.Subsystems.Intake;

import edu.wpi.first.units.measure.Angle;


public enum IntakeStates {
    INTAKE(IntakeConstants.INTAKE_SPEED, IntakeConstants.ARM_ANGLE_INTAKE),
    OUTTAKE(IntakeConstants.OUTTAKE_SPEED, IntakeConstants.ARM_ANGLE_OUTTAKE),
    IDLE(IntakeConstants.IDLE_SPEED, IntakeConstants.ARM_ANGLE_IDLE),
    AGITATE(IntakeConstants.IDLE_SPEED, IntakeConstants.ARM_ANGLE_AGITATE_LOW);
    
    private double wheelSpeed;
    private Angle armAngle;

    IntakeStates(double wheelSpeed, Angle armAngle) {
        this.wheelSpeed = wheelSpeed;
        this.armAngle = armAngle;
    }

    public double getWheelSpeed() {
        return wheelSpeed;
    }

    public Angle getArmAngle() {
        return armAngle;
    }
}


