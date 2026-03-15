package frc.robot.Manager;

// import frc.robot.Subsystems.AutoAlign.AutoAlign;
import frc.robot.Subsystems.Climber.ClimberStates;
import frc.robot.Subsystems.Intake.IntakeStates;
import frc.robot.Subsystems.Passthrough.PassthroughStates;
import frc.robot.Subsystems.Shooter.ShooterStates;

public enum ManagerStates {
    // climber should have intake out
    CLIMBOUT(ClimberStates.GOINGOUT, IntakeStates.IDLE, PassthroughStates.IDLE, ShooterStates.IDLE, "CLIMBOUT"),
    CLIMBIN(ClimberStates.COMINGIN, IntakeStates.IDLE, PassthroughStates.IDLE, ShooterStates.IDLE, "CLIMBIN"),
    INIDLE(ClimberStates.IDLE, IntakeStates.IDLE, PassthroughStates.IDLE, ShooterStates.IDLE, "REALLYIDLE"),
    IDLE(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.IDLE, ShooterStates.IDLE, "IDLE"),
    INTAKING(ClimberStates.IDLE, IntakeStates.INTAKING, PassthroughStates.IDLE, ShooterStates.IDLE, "INTAKING"),
    DYNAMICSHOT(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.PASS, ShooterStates.DYNAMICSHOOT, "DYNAMICSHOT"),
    LONGSHOT(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.PASS, ShooterStates.LONGPASS, "LONGPASS"),
    FIXEDSHOT(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.PASS, ShooterStates.FIXEDSHOOT, "FIXEDSHOOT");

    private final String stateString;
    private final IntakeStates intakeState;
    private final ShooterStates shooterState;
    private final ClimberStates climberState;
    private final PassthroughStates passthroughState;

    ManagerStates(ClimberStates climberState, IntakeStates intakeState, PassthroughStates passthroughState, ShooterStates shooterState, String stateString) {
        this.climberState = climberState;
        this.intakeState = intakeState;
        this.passthroughState = passthroughState;
        this.shooterState = shooterState;
        this.stateString = stateString;

    }

    public ClimberStates getClimberState() {
        return climberState;
    }

    public IntakeStates getIntakeState() {
        return intakeState;
    }

    public PassthroughStates getPassthroughState() {
        return passthroughState;
    }

    public ShooterStates getShooterState() {
        return shooterState;
    }

    // public AutoAlign getAutoAlignState() {
    //     return autoalign;
    // }

    public String getStateString() {
        return stateString;
    }

}
