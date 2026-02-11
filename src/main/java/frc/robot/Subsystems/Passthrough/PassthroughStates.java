package frc.robot.Subsystems.Passthrough;

import edu.wpi.first.units.measure.AngularVelocity;

import static frc.robot.Subsystems.Passthrough.PassthroughConstants.*;

public enum PassthroughStates {
    IDLE(IDLE_SPEED),
    PASS(PASS_SPEED);

    AngularVelocity Speed;
    PassthroughStates(AngularVelocity Speed) {
        this.Speed = Speed;
    }

    public AngularVelocity getSpeed() {
        return Speed;
    }


}
