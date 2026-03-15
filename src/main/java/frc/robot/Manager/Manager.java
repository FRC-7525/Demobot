package frc.robot.Manager;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.Subsystems.AutoAlign.AutoAlign;
import frc.robot.Subsystems.Climber.Climber;
import frc.robot.Subsystems.Drive.Drive;
import frc.robot.Subsystems.Intake.Intake;
import frc.robot.Subsystems.Passthrough.Passthrough;
import frc.robot.Subsystems.Shooter.Shooter;
import static frc.robot.Manager.ManagerConstants.*;

import static frc.robot.Manager.ManagerStates.*;

import javax.sql.rowset.RowSetProvider;

import org.littletonrobotics.junction.Logger;
public class Manager {
    Climber climber;
    Intake intake;
    Passthrough passthrough;
    Shooter shooter;
    ManagerStates robotstate;
    //AutoAlign autoalign;
    boolean intakeOut;

    private XboxController driverController = new XboxController(DRIVER_CONTROLLER_PORT);
    private XboxController operatorController = new XboxController(OPERATOR_CONTROLLER_PORT);

    private static Manager instance;

    private Manager() {
        climber = Climber.getInstance();
        intake = Intake.getInstance();
        passthrough = Passthrough.getInstance();
        shooter = Shooter.getInstance();

        robotstate = IDLE;
        intakeOut = false;
    }

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

    public void periodic() {


        //IDLE All
    if(driverController.getYButtonPressed()) {
        robotstate = IDLE;
 }
    
        // SHOOTING Fixed
    if (driverController.getLeftBumperButtonPressed()) {
        if(robotstate == IDLE) {
            robotstate = FIXEDSHOT;
        }
        else if(robotstate == FIXEDSHOT) {
            robotstate = IDLE;
        }
    }
    // SHOOTING DYNAMIC
    if (driverController.getAButtonPressed()) {
        if(robotstate == IDLE) {
            robotstate = DYNAMICSHOT;
        }
        else if(robotstate == DYNAMICSHOT) {
            robotstate = IDLE;
        }
    }

    // SHOOTING LONG
    if (driverController.getRightBumperButtonPressed()) {
        if(robotstate == IDLE) {
            robotstate = LONGSHOT;
        }
        else if(robotstate == LONGSHOT) {
            robotstate = IDLE;
        }
    }

    // INTAKING 
    if (driverController.getXButtonPressed()) {
        if(robotstate == IDLE) {
            robotstate = INTAKING;
        }
        else if(robotstate == INTAKING) {
            robotstate = IDLE;
        }
    } 

    //OPERATOR OVERRIDE INTAKE OUT
    if (operatorController.getBButtonPressed()) {
        if(robotstate == IDLE) {
            robotstate = INIDLE;
        }
        else if(robotstate == INIDLE) {
            robotstate = IDLE;
        }
    } 

    // CLIMBING
    if (operatorController.getYButtonPressed()) {
        if(robotstate == IDLE) {
            robotstate = CLIMBPREP;
        }
        else if(robotstate == CLIMBPREP) {
            robotstate = CLIMBLV1;
        }
        else if(robotstate == CLIMBLV1) {
            robotstate = CLIMBLV2;
        }
        else if(robotstate == CLIMBLV2) {
            robotstate = IDLE;
        }
    } 





        intake.setState(getState().getIntakeState());
        passthrough.setState(getState().getPassthroughState());
        shooter.setState(getState().getShooterState());
        climber.setState(getState().getClimberState());
        // autoalign.getAutoAlignState();
        Logger.recordOutput("Manager State", robotstate.getStateString());
        // Logger.recordOutput("AutoAlign State", autoalign.getInstance().getAutoAlignState());

        //Intake.getInstance().periodic();
        Passthrough.getInstance().periodic();
        Shooter.getInstance().periodic();
        Climber.getInstance().periodic();
        Drive.getInstance().periodic();

        SmartDashboard.putString("Manager State", robotstate.getStateString());

        
    }

    public ManagerStates getState() {
        return robotstate;
    }

}
