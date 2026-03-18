package frc.robot.Subsystems.Drive;

import com.ctre.phoenix6.swerve.jni.SwerveJNI.DriveState;

public enum DriveStates {
	Manual("Manual");

	public String stateString;

	public DriveStates driveState;


	DriveStates( String stateString) {
		this.stateString = stateString;
	}

	public String getStateString() {
		return stateString;
	}



}