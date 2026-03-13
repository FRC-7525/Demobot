package frc.robot.Manager;

// import frc.robot.Subsystems.AutoAlign.AutoAlign;
import frc.robot.Subsystems.Climber.ClimberStates;
import frc.robot.Subsystems.Intake.IntakeStates;
import frc.robot.Subsystems.Passthrough.PassthroughStates;
import frc.robot.Subsystems.Shooter.ShooterStates;

public enum ManagerStates {
    // climber should have intake out
    CLIMBPREP(ClimberStates.DEPLOY, IntakeStates.IDLE, PassthroughStates.IDLE, ShooterStates.IDLE, "CLIMBPREP"),
    CLIMBLV1(ClimberStates.L1, IntakeStates.IDLE, PassthroughStates.IDLE, ShooterStates.IDLE, "CLIMBLV1"),
    CLIMBLV2(ClimberStates.L2, IntakeStates.IDLE, PassthroughStates.IDLE, ShooterStates.IDLE, "CLIMBLV2"),
    IDLE(ClimberStates.IDLE, IntakeStates.IDLE, PassthroughStates.IDLE, ShooterStates.IDLE, "IDLE"),
    IDLEINTAKEOUT(ClimberStates.IDLE, IntakeStates.INTAKINGNOROLLER, PassthroughStates.IDLE, ShooterStates.IDLE, "IDLE"),
    INTAKING(ClimberStates.IDLE, IntakeStates.INTAKING, PassthroughStates.IDLE, ShooterStates.IDLE, "INTAKING"),
    DYNAMICSHOT(ClimberStates.IDLE, IntakeStates.INTAKINGNOROLLER, PassthroughStates.PASS, ShooterStates.DYNAMICSHOOT, "DYNAMICSHOT"),
    LONGSHOT(ClimberStates.IDLE, IntakeStates.INTAKINGNOROLLER, PassthroughStates.PASS, ShooterStates.LONGPASS, "LONGPASS"),
    FIXEDSHOT(ClimberStates.IDLE, IntakeStates.INTAKING, PassthroughStates.PASS, ShooterStates.FIXEDSHOOT, "FIXEDSHOOT");

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
