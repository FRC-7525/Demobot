// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Manager.Manager;
import frc.robot.Subsystems.Drive.Drive;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
    private final Manager manager = Manager.getInstance();
	private final Drive drive = Drive.getInstance();

	public static boolean isRedAllianceInactive;
	/**
	 * This function is run when the robot is first started up and should be used for any
	 * initialization code.
	 */
	


	public Robot() {

		SmartDashboard.putNumber("Match Info/Match Number", DriverStation.getMatchNumber());
		SmartDashboard.putBoolean("Robot State/isEnabled", DriverStation.isEnabled());
		SmartDashboard.putBoolean("Robot State/isAutonomous", DriverStation.isAutonomous());
		SmartDashboard.putString("Match Info/Match Type", DriverStation.getMatchType().toString());
		SmartDashboard.putString("Match Info/Event Name", DriverStation.getEventName());
		SmartDashboard.putBoolean("Match Info/redHubActive", true);
		Optional<DriverStation.Alliance> alliance = DriverStation.getAlliance();



		if (alliance.isPresent() && alliance.get() == DriverStation.Alliance.Red) {
			SmartDashboard.putString("Match Info/Alliance Color", "Red");
		} else if (alliance.isPresent() && alliance.get() == DriverStation.Alliance.Blue) {
			SmartDashboard.putString("Match Info/Alliance Color", "Blue");
		}
	}

	@Override
	public void robotPeriodic() {
		manager.periodic();
		drive.periodic();
		SmartDashboard.putNumber("Match Info/Time Left in Match", DriverStation.getMatchTime());
	}

	@Override
	public void autonomousInit() {
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
