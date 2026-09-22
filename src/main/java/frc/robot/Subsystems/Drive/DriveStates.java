package frc.robot.Subsystems.Drive;

import static frc.robot.GlobalConstants.Controllers.OPERATOR_CONTROLLER;
import static frc.robot.Subsystems.Drive.DriveConstants.*;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public enum DriveStates {
	MANUAL("Manual", () -> {
		Drive.getInstance().getSwerveInputs().scaleTranslation(MANUAL_SCALE);
		Drive.getInstance().getSwerveInputs().scaleRotation(MANUAL_SCALE);
		Drive.getInstance().getSwerveDrive().driveFieldOriented(Drive.getInstance().getSwerveInputs().get());
	}),
	DEMO("Demo", () -> {
		if (OPERATOR_CONTROLLER.getAButton()) {
			Drive.getInstance().getSwerveInputs().scaleTranslation(DEMO_SCALE);
			Drive.getInstance().getSwerveInputs().scaleRotation(DEMO_SCALE);
			Drive.getInstance().getSwerveDrive().driveFieldOriented(Drive.getInstance().getSwerveInputs().get());
		} else {
			Drive.getInstance().getSwerveDrive().driveFieldOriented(ChassisSpeeds.fromRobotRelativeSpeeds(0, 0, 0, Rotation2d.fromDegrees(0)));
		}
	}),
	DISABLED("Disabled", () -> {
		Drive.getInstance().getSwerveDrive().driveFieldOriented(ChassisSpeeds.fromRobotRelativeSpeeds(0, 0, 0, Rotation2d.fromDegrees(0)));
	}),
	SLOW("Slow", () -> {
		Drive.getInstance().getSwerveInputs().scaleTranslation(SLOW_SCALE);
		Drive.getInstance().getSwerveInputs().scaleRotation(SLOW_SCALE);
		Drive.getInstance().getSwerveDrive().driveFieldOriented(Drive.getInstance().getSwerveInputs().get());
	});

	private final String stateString;
	private final Runnable driveCommand;

	DriveStates(String stateString, Runnable driveCommand) {
		this.stateString = stateString;
		this.driveCommand = driveCommand;
	}

	public String getStateString() {
		return stateString;
	}

	public void drive() {
		driveCommand.run();
	}
}
