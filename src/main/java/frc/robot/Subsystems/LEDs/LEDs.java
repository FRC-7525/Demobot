package frc.robot.Subsystems.LEDs;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AddressableLEDBufferView;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LEDs {
    AddressableLED ledStrip;
    AddressableLEDBuffer ledStripBuffer;
    AddressableLEDBuffer ledBuffer;
    AddressableLEDBufferView bottom;
    AddressableLEDBufferView intake;
    AddressableLEDBufferView rightBody;
    AddressableLEDBufferView leftBody;
    AddressableLEDBufferView rightPanel;
    AddressableLEDBufferView leftPanel;
    AddressableLEDBufferView allLed;
    LEDStates state;
    private static LEDs instance;

    public LEDs() {
        ledStrip = new AddressableLED(9);
        //ledStripBuffer = new AddressableLEDBuffer(24);

        ledBuffer = new AddressableLEDBuffer(264);
        ledStrip.setLength(ledBuffer.getLength());


        bottom = ledBuffer.createView(0, 67);
        intake = ledBuffer.createView(68, 92);
        rightBody = ledBuffer.createView(93, 117);
        leftBody = ledBuffer.createView(118, 172);
        rightPanel = ledBuffer.createView(173, 227);
        leftPanel = ledBuffer.createView(228, 263);
        allLed = ledBuffer.createView(0,263);
        state = LEDStates.IDLE;

        ledStrip.setData(ledBuffer);
        ledStrip.start();
    }

    public void periodic() {
        ledStrip.setData(ledBuffer);
        SmartDashboard.putBoolean("hehe", true);
    }

    public static LEDs getInstance() {
		if (instance == null) {
			instance = new LEDs();
        }
                return instance;
    }

    public void setState(LEDStates state) {
		this.state = state;
        if (state == LEDStates.INTAKING) {
            state.getPattern().applyTo(allLed);
        } else {
        state.getPattern().applyTo(bottom);
        state.getPattern().applyTo(intake);
        state.getPattern().applyTo(rightBody);
        state.getPattern().applyTo(leftBody);
        state.getPattern().applyTo(rightPanel);
        state.getPattern().applyTo(leftPanel);
        }
	}
}