# Demobot Setup Guide

Use this guide to get a laptop and the robot ready for coding, deploying, and connecting.

## Robot hardware context

The Demobot uses:
- A full WAGO CAN loop
- SPARK MAX motor controllers with NEO motors
- A PDH for power distribution
- Anderson power connectors

## Before you start

- Use a Windows laptop (Driver Station laptop preferred)
- Ensure 2026 WPILib is installed
- Ensure FRC Driver Station is installed
- Ensure the laptop Wi-Fi works
- Keep the robot on blocks or in a safe area before enabling
- Use a charged battery and confirm main breaker access

## 1) Open WPILib VS Code

1. Press the Windows key.
2. Search for **2026 WPILib VS Code** and open it.

**Picture placeholder:** WPILib VS Code in the Windows Start menu search results.

## 2) Clone the repository

1. In WPILib VS Code, open **File > New Window**.
2. In the new window, click **Clone GitHub Repository**.
3. Paste this repository URL and press Enter:
   - `https://github.com/FRC-7525/Demobot.git`
4. Select a local folder and wait for clone to finish.

**Picture placeholder:** VS Code clone prompt with the Demobot repository URL pasted.

## 3) Build robot code

1. Press **Ctrl+Shift+P**.
2. Run **WPILib: Build Robot Code**.
3. Confirm you get a successful build message.
4. If build fails, open [Troubleshooting Guide](./troubleshooting.md) and use **Build fails in WPILib VS Code**.

**Picture placeholder:** Terminal or WPILib status showing a successful build.

## 4) Power and network the robot

1. Power on the robot and wait for full boot.
2. Connect the laptop to Wi-Fi network **Demobot**.
3. Use the team-provided Wi-Fi password.
4. Disable VPN during deploy and driver station use.

**Picture placeholder:** Windows Wi-Fi panel connected to the Demobot SSID.

## 5) Connect Driver Station

1. Open **FRC Driver Station**.
2. Wait for communications to come up.
3. Confirm status lights are green (Robot, Comm, and Code).
4. Open Elastic Dashboard if it did not open automatically.

**Picture placeholder:** Driver Station showing green status indicators.

## 6) Deploy code

1. In WPILib VS Code, press **Ctrl+Shift+P**.
2. Run **WPILib: Deploy Robot Code**.
3. Wait for deploy to complete.
4. If deploy appears stuck for several minutes, cancel and retry once.
5. If retry fails, use [Troubleshooting Guide](./troubleshooting.md) and see **Deploy fails or hangs**.

**Picture placeholder:** Deploy command output showing successful deployment to roboRIO.

## 7) Validation after first deploy

- Robot remains connected in Driver Station
- Code indicator remains green
- Dashboard values update as expected
- No new major CAN or power warnings
- Robot can safely enable/disable once on blocks

**Picture placeholder:** Dashboard showing live robot telemetry values after deploy.

## 8) If setup fails

1. Do not keep retrying random steps.
2. Capture Driver Station status screenshot.
3. Save build/deploy error output.
4. Use [Troubleshooting Guide](./troubleshooting.md) section matching your symptom.
5. Escalate with collected evidence.

## Next step

After setup is complete, continue with the [Operations Guide](./operations-guide.md).
