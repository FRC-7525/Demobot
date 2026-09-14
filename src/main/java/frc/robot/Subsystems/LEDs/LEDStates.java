package frc.robot.Subsystems.LEDs;

import edu.wpi.first.wpilibj.LEDPattern;

public enum LEDStates {
    OFF(),
    IDLE(),
    INTAKING(),
    SHOOTING();

    LEDPattern off;
    LEDPattern gradientPattern;
    LEDPattern gradientScrollingPattern;
    LEDPattern blinkingPattern;
}
