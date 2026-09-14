package frc.robot.Subsystems.LEDs;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AddressableLEDBufferView;

public class LEDs {
    AddressableLED ledStrip;
    AddressableLEDBuffer ledStripBuffer;
    AddressableLEDBuffer ledBuffer;

    public LEDs() {
        ledStrip = new AddressableLED(0);
        ledStripBuffer = new AddressableLEDBuffer(60);

        ledStrip.setLength(ledStripBuffer.getLength());

        ledStrip.setData(ledStripBuffer);
        ledStrip.start();

        AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(120);

        AddressableLEDBufferView bottom = ledBuffer.createView(0, 19);
        AddressableLEDBufferView intake = ledBuffer.createView(20, 39);
        AddressableLEDBufferView rightBody = ledBuffer.createView(40, 59);
        AddressableLEDBufferView leftBody = ledBuffer.createView(60, 79);
        AddressableLEDBufferView rightPanel = ledBuffer.createView(80, 99);
        AddressableLEDBufferView leftPanel = ledBuffer.createView(100, 119);
  }
  
}