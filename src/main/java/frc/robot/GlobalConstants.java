package frc.robot;

public class GlobalConstants {

	public enum RobotMode {
		REAL,
		SIM,
	}

	// Global constants can be defined here
	public static final RobotMode ROBOT_MODE = RobotMode.SIM; // Change this to REAL when deploying to the actual robot

	public enum RobotFeedforwardMode {
		REAL,
		SIM,
	}

	public static final RobotFeedforwardMode ROBOT_FEEDFORWARD_MODE = RobotFeedforwardMode.SIM; // same comment as above
}
