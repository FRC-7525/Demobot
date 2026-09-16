package frc.robot.Subsystems.LEDs;

import edu.wpi.first.wpilibj.LEDPattern;

import static edu.wpi.first.units.Units.Percent;
import static edu.wpi.first.units.Units.Second;
import static edu.wpi.first.units.Units.Seconds;

import java.util.Map;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.AddressableLED.ColorOrder;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern.GradientType;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Subsystems.LEDs.*;
import frc.robot.Subsystems.Shooter.Shooter;



public enum LEDStates {
    OFF(LEDPattern.kOff),
    IDLE(LEDPattern.gradient(GradientType.kContinuous, Color.kBlue, Color.kOrangeRed)),
    INTAKING(LEDPattern.gradient(GradientType.kContinuous, Color.kOrangeRed, Color.kBlue).scrollAtRelativeSpeed(Percent.per(Second).of(25))),
    SHOOTING(LEDPattern.steps(Map.of(0.0, Color.kBlue, 0.5, Color.kOrangeRed)).blink(Seconds.of(1)));
    //SHOOTING(LEDPattern.gradient(GradientType.kContinuous, Color.kBlue, Color.kOrangeRed).blink(Seconds.of(1.5)));
    // IDLE(LEDPattern.gradient(GradientType.kDiscontinuous, Color.kRed, Color.kBlue).mask(LEDPattern.progressMaskLayer(() -> m_elevator.getHeight() / m_elevator.getMaxHeight()))),
    // IDLE(LEDPattern.solid(Color.kRed).breathe(Seconds.of(2))),
    //INTAKING(LEDPattern.gradient(GradientType.kContinuous, Color.kBlue, Color.kOrange)),
    //IDLE(LEDPattern.gradient(GradientType.kContinuous, Color.kBlue, Color.kOrangeRed).blink(Seconds.of(1.5))),
    // IDLE(LEDPattern.rainbow(255, 255).mask(LEDPattern.steps(Map.of(0.0, Color.kWhite, 0.5, Color.kBlack)).scrollAtRelativeSpeed(Percent.per(Second).of(10)))),



    private LEDPattern pattern;

    LEDStates(LEDPattern pattern) {
        this.pattern = pattern;
    }

    public LEDPattern getPattern() {
		    return this.pattern;
	}

    // // LEDPattern steps = LEDPattern.steps(Map.of(0, Color.kRed, 0.5, Color.kBlue));
    // Map<Double, Color> maskSteps = Map.of(0.0, Color.kWhite, 0.5, Color.kBlack);
    // LEDPattern base = LEDPattern.rainbow(255, 255);
    // LEDPattern mask = LEDPattern.steps(maskSteps).scrollAtRelativeSpeed(Percent.per(Second).of(0.25));

    // LEDPattern red = base.mask(mask);
}




