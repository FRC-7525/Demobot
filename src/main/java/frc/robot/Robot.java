// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.DriverStation;
import java.util.Optional;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.AitanAndJamesAreTheBestForSureAutos.AutoBuilderStuff;
import frc.robot.AitanAndJamesAreTheBestForSureAutos.AutoCommands;
import frc.robot.Manager.Manager;
import frc.robot.Manager.ManagerStates;
import frc.robot.Subsystems.Drive.Drive;
import frc.robot.Subsystems.Drive.DriveStates;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
    private final Manager manager = Manager.getInstance();
	private final Drive drive = Drive.getInstance();
	private final AutoCommands autoCommands = AutoCommands.getInstance(); 
	private SendableChooser<Command> autoChooser;

	public static boolean isRedAllianceInactive;
	/**
	 * This function is run when the robot is first started up and should be used for any
	 * initialization code.
	 */

	public Robot() {
		AutoBuilderStuff.setConfig();
		DriverStation.silenceJoystickConnectionWarning(true);
		CommandScheduler.getInstance().unregisterAllSubsystems();
		NamedCommands.registerCommand("Deploy Intake", autoCommands.intakeDeploy());
		NamedCommands.registerCommand("IDLE", autoCommands.returnToIdle());
		NamedCommands.registerCommand("WindUp", autoCommands.startWindingUp());
		NamedCommands.registerCommand("Shoot", autoCommands.shootFuel());
		autoChooser = AutoBuilder.buildAutoChooser();
		SmartDashboard.putData("Auto Chooser", autoChooser);

		SmartDashboard.putNumber("Match Info/Match Number", DriverStation.getMatchNumber());
		SmartDashboard.putBoolean("Robot State/isEnabled", DriverStation.isEnabled());
		SmartDashboard.putBoolean("Robot State/isAutonomous", DriverStation.isAutonomous());
		SmartDashboard.putString("Match Info/Match Type", DriverStation.getMatchType().toString());
		SmartDashboard.putString("Match Info/Event Name", DriverStation.getEventName());
		SmartDashboard.putBoolean("Match Info/redHubActive", true);
		// Optional<DriverStation.Alliance> alliance = DriverStation.getAlliance();
	}

	@Override
	public void robotPeriodic() {
		manager.periodic();
		drive.periodic();
		CommandScheduler.getInstance().run();
		SmartDashboard.putNumber("Match Info/Time Left in Match", DriverStation.getMatchTime());
	}

	@Override
	public void autonomousInit() {
		drive.setState(DriveStates.Auto);
		Command autoCommand = autoChooser.getSelected();
		if (autoCommand != null) {
			CommandScheduler.getInstance().schedule(autoCommand);
		}	
		SmartDashboard.putBoolean("Robot State/isAutonomous", DriverStation.isAutonomous());
		SmartDashboard.putBoolean("Robot State/isEnabled", DriverStation.isEnabled());
		SmartDashboard.putString("Match Info/Match Phase", "Autonomous");
	}

	@Override
	public void autonomousPeriodic() {
		if (DriverStation.getMatchTime() <= 20) {
			SmartDashboard.putBoolean("Match Info/redHubActive", true);
			SmartDashboard.putBoolean("Match Info/blueHubActive", true);
		}
	}

	@Override
	public void teleopInit() {
		drive.setState(DriveStates.Manual);
		CommandScheduler.getInstance().cancelAll();
		manager.setState(ManagerStates.IDLE);
		SmartDashboard.putBoolean("Robot State/isAutonomous", DriverStation.isAutonomous());
		SmartDashboard.putBoolean("Robot State/isEnabled", DriverStation.isEnabled());
		SmartDashboard.putString("Match Info/Match Phase", "Teleoperated");
	}

	@Override
	public void teleopPeriodic() {
		if (DriverStation.getMatchTime() <= 140 & DriverStation.getMatchTime() > 130) {
			SmartDashboard.putBoolean("Match Info/redHubActive", true);
			SmartDashboard.putBoolean("Match Info/blueHubActive", true);
		} else if (DriverStation.getMatchTime() <= 130 & DriverStation.getMatchTime() > 105) {
			if (DriverStation.getGameSpecificMessage().charAt(0) == 'R') {
				isRedAllianceInactive = true;
			} else if (DriverStation.getGameSpecificMessage().charAt(0) == 'B') {
				isRedAllianceInactive = false;
			}
			SmartDashboard.putBoolean("Match Info/redHubActive", !isRedAllianceInactive);
			SmartDashboard.putBoolean("Match Info/blueHubActive", isRedAllianceInactive);
		} else if (DriverStation.getMatchTime() <= 105 & DriverStation.getMatchTime() > 80) {
			SmartDashboard.putBoolean("Match Info/redHubActive", isRedAllianceInactive);
			SmartDashboard.putBoolean("Match Info/blueHubActive", !isRedAllianceInactive);
		} else if (DriverStation.getMatchTime() <= 80 & DriverStation.getMatchTime() > 55) {
			SmartDashboard.putBoolean("Match Info/redHubActive", !isRedAllianceInactive);
			SmartDashboard.putBoolean("Match Info/blueHubActive", isRedAllianceInactive);
		} else if (DriverStation.getMatchTime() <= 55 & DriverStation.getMatchTime() > 30) {
			SmartDashboard.putBoolean("Match Info/redHubActive", isRedAllianceInactive);
			SmartDashboard.putBoolean("Match Info/blueHubActive", !isRedAllianceInactive);
		} else if (DriverStation.getMatchTime() <= 30) {
			SmartDashboard.putBoolean("Match Info/redHubActive", true);
			SmartDashboard.putBoolean("Match Info/blueHubActive", true);
		}
	}

	@Override
	public void disabledInit() {
		SmartDashboard.putBoolean("Robot State/isAutonomous", DriverStation.isAutonomous());
		SmartDashboard.putBoolean("Robot State/isEnabled", DriverStation.isEnabled());
	}

	@Override
	public void disabledPeriodic() {}

	@Override
	public void testInit() {}

	@Override
	public void testPeriodic() {}

	@Override
	public void simulationInit() {}

	@Override
	public void simulationPeriodic() {}
}
