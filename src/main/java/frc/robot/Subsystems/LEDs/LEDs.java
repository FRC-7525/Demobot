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
    LEDStates state;
    private static LEDs instance;

    public LEDs() {
        ledStrip = new AddressableLED(9);
        //ledStripBuffer = new AddressableLEDBuffer(24);

        ledBuffer = new AddressableLEDBuffer(30);
        ledStrip.setLength(ledBuffer.getLength());


        bottom = ledBuffer.createView(0, 4);
        intake = ledBuffer.createView(5, 9);
        rightBody = ledBuffer.createView(10, 14);
        leftBody = ledBuffer.createView(15, 19);
        rightPanel = ledBuffer.createView(20, 24);
        leftPanel = ledBuffer.createView(25, 29);
        state = LEDStates.IDLE;

        ledStrip.setData(ledBuffer);
        ledStrip.start();
        state.getPattern().applyTo(bottom);
        state.getPattern().applyTo(intake);
        state.getPattern().applyTo(rightBody);
        state.getPattern().applyTo(leftBody);
        state.getPattern().applyTo(rightPanel);
        state.getPattern().applyTo(leftPanel);
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
	}
}