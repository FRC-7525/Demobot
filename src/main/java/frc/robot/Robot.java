// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
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

	public static boolean isRedAlliance;
	/**
	 * This function is run when the robot is first started up and should be used for any
	 * initialization code.
	 */

	public Robot() {
		DataLogManager.start();
		DataLogManager.logNetworkTables(true);
		DataLogManager.logConsoleOutput(true);
		AutoBuilderStuff.setConfig();
		DriverStation.silenceJoystickConnectionWarning(true);
		CommandScheduler.getInstance().unregisterAllSubsystems();
		NamedCommands.registerCommand("Deploy Intake", autoCommands.intakeDeploy());
		NamedCommands.registerCommand("IDLE", autoCommands.returnToIdle());
		NamedCommands.registerCommand("WindUp", autoCommands.startWindingUp());
		NamedCommands.registerCommand("Shoot", autoCommands.shootFuel());
		autoChooser = AutoBuilder.buildAutoChooser();
		SmartDashboard.putData("Auto Chooser", autoChooser);
		drive.zeroGyro();
	}

	@Override
	public void robotPeriodic() {
		manager.periodic();
		drive.periodic();
		CommandScheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		drive.zeroGyro();
		drive.setState(DriveStates.Auto);
		Command autoCommand = autoChooser.getSelected();
		if (autoCommand != null) {
			CommandScheduler.getInstance().schedule(autoCommand);
		}	
	}

	@Override
	public void autonomousPeriodic() {}

	@Override
	public void teleopInit() {
		drive.setState(DriveStates.Manual);
		CommandScheduler.getInstance().cancelAll();
		manager.setState(ManagerStates.IDLE);
	}

	@Override
	public void teleopPeriodic() {}

	@Override
	public void disabledInit() {}

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
