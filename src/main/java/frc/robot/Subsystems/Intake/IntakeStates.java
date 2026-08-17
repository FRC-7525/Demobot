package frc.robot.Subsystems.Intake;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public enum IntakeStates {
    INTAKE(IntakeConstants.INTAKE_SPEED, IntakeConstants.ARM_ANGLE_INTAKE),
    OUTTAKE(IntakeConstants.OUTTAKE_SPEED, IntakeConstants.ARM_ANGLE_OUTTAKE),
    IDLE(IntakeConstants.IDLE_SPEED, IntakeConstants.ARM_ANGLE_IDLE);
    
    private AngularVelocity wheelSpeed;
    private Angle armAngle;

    IntakeStates(AngularVelocity wheelSpeed, Angle armAngle) {
        this.wheelSpeed = wheelSpeed;
        this.armAngle = armAngle;
    }

    public AngularVelocity getWheelSpeed() {
        return wheelSpeed;
    }

    public Angle getArmAngle() {
        return armAngle;
    }
}


