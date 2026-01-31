package frc.robot.Subsystems.Shooter;

import static frc.robot.Constants.Shooter.*;

enum ShooterStates {
    IDLE(0), 
    FIXEDSHOOT(FIXED_SHOOT_RPS),
    DYNAMICSHOOT(FIXED_SHOOT_RPS),
    LONGPASS(LONG_PASS_RPS);

    private String stateString;
    private double shooterSpeed;

    ShooterStates(double shooterSpeed) {
        this.stateString = this.name();
        this.shooterSpeed = shooterSpeed;
    }

    public String getStateString() {
        return stateString;
    }

    public Double getShooterRPS() {
        return shooterSpeed;
    }
}