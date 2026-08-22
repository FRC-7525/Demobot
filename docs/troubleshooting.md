# Demobot Troubleshooting Guide

Use this guide when setup, build, deploy, or robot operation fails.

## Quick triage order (60-second check)

1. **Power:** Battery charged, breaker on, Anderson connectors fully seated.
2. **Network:** Laptop connected to the **Demobot** Wi-Fi network.
3. **Driver Station:** Robot/Comm/Code indicators checked.
4. **Build:** Robot code builds successfully.
5. **Deploy:** Code deploys successfully to roboRIO.
6. **Hardware:** CAN and PDH wiring has no loose connections.

If any step fails, go to the matching section below.

---

## Problem index (symptom → section)

- Driver Station not connecting → [Driver Station shows no communication](#driver-station-shows-no-communication)
- Build fails in WPILib VS Code → [Build fails in WPILib VS Code](#build-fails-in-wpilib-vs-code)
- Deploy fails or hangs → [Deploy fails or hangs](#deploy-fails-or-hangs)
- Robot has Comm but no Code → [Robot has communication but no code](#robot-has-communication-but-no-code)
- Robot enables briefly then resets/browns out → [Brownouts or random reboots](#brownouts-or-random-reboots)
- Motors/controllers missing or intermittent → [CAN bus issues (WAGO loop, SPARK MAX, NEO)](#can-bus-issues-wago-loop-spark-max-neo)
- Joystick/controller not working → [Joystick or controller input not working](#joystick-or-controller-input-not-working)
- Robot will not enable → [Robot will not enable](#robot-will-not-enable)
- Dashboard/telemetry not updating → [Dashboard values not updating](#dashboard-values-not-updating)
- Wi-Fi disconnects often → [Intermittent Wi-Fi or high packet loss](#intermittent-wi-fi-or-high-packet-loss)

---

## Driver Station shows no communication

### Symptoms
- Driver Station Comm indicator is red/yellow.
- Ping to roboRIO fails.
- Deploy cannot find target roboRIO.

### Likely causes
- Laptop connected to wrong network.
- Robot still booting or unpowered.
- Firewall/network profile blocking robot traffic.

### Checks and fixes
1. Confirm laptop is connected to **Demobot** SSID.
2. Power-cycle robot and wait full boot.
3. Close/reopen Driver Station.
4. Temporarily disable VPN if enabled.
5. Ensure current Windows network profile is not restricting local traffic.

### If still failing
- Restart laptop Wi-Fi adapter.
- Restart laptop and robot, then retry.

---

## Build fails in WPILib VS Code

### Symptoms
- `WPILib: Build Robot Code` fails.
- Gradle/Java errors appear in terminal.

### Likely causes
- Missing/broken dependencies.
- Environment mismatch.
- Real compile error in code.

### Checks and fixes
1. Scroll to the **first real error** in terminal output.
2. Re-run build once after restarting WPILib VS Code.
3. From project root, run:
   - `./gradlew build` (PowerShell: `gradlew.bat build`)
4. If dependency download failed, check internet and retry.
5. If only one teammate fails while others build, delete local Gradle cache and rebuild.

### If still failing
- Capture first error block and share with mentor/programming lead.

---

## Deploy fails or hangs

### Symptoms
- `WPILib: Deploy Robot Code` times out, stalls, or exits with error.

### Likely causes
- Unstable comms to roboRIO.
- Robot not fully booted.
- Previous deploy interrupted.

### Checks and fixes
1. Verify Driver Station comm is stable before deploy.
2. Cancel deploy once, then retry.
3. Reboot robot and retry deploy.
4. Keep laptop on robot Wi-Fi only during deploy.
5. Run a fresh build, then deploy again.

### If still failing
- Try deploy from the Driver Station laptop to isolate local machine issues.

---

## Robot has communication but no code

### Symptoms
- Comm is green, Code indicator is not green.
- Robot cannot be enabled for normal operation.

### Likely causes
- Code crashed on startup.
- Wrong project/version deployed.
- Deploy did not complete cleanly.

### Checks and fixes
1. Rebuild and redeploy from this repository.
2. Check terminal output for runtime/startup exceptions.
3. Confirm the most recent deploy was successful.
4. Reboot robot after deploy.

### If still failing
- Collect startup logs and escalate.

---

## Brownouts or random reboots

### Symptoms
- Robot reboots under load.
- Subsystems reset intermittently.
- Driver Station reports brownout behavior.

### Likely causes
- Weak/discharged battery.
- Loose/high-resistance main power connection.
- PDH wiring not secure.

### Checks and fixes
1. Swap to a known-good charged battery.
2. Inspect Anderson connectors for full seating and heat damage.
3. Verify PDH main and branch connections are tight.
4. Check for cable movement causing resets.

### If still failing
- Reduce load and test subsystems one at a time to isolate the offender.

---

## CAN bus issues (WAGO loop, SPARK MAX, NEO)

### Symptoms
- Controllers disappear intermittently.
- CAN warnings/errors in logs.
- Motors do not respond consistently.

### Likely causes
- Open CAN loop.
- Loose/incorrect CAN wiring.
- ID conflicts or missing devices.

### Checks and fixes
1. Inspect CAN loop continuity end-to-end through WAGO and all SPARK MAX devices.
2. Confirm CAN high/low polarity is correct at each connection.
3. Check each SPARK MAX reports the expected CAN ID.
4. Reseat loose connectors and repair damaged cable sections.
5. Reboot after wiring corrections.

### If still failing
- Isolate by disconnecting non-essential nodes and reintroducing one at a time.

---

## Joystick or controller input not working

### Symptoms
- Driver Station does not show joystick activity.
- Robot does not respond to teleop controls.

### Likely causes
- USB not detected.
- Wrong joystick mapping/order.
- Driver Station not focused/configured.

### Checks and fixes
1. Replug controller USB and verify it appears in Driver Station USB tab.
2. Confirm expected joystick port/order.
3. Restart Driver Station.
4. Test with a known-good controller.

### If still failing
- Reboot laptop and retest with only required controllers connected.

---

## Robot will not enable

### Symptoms
- Enable attempt fails or immediately disables.

### Likely causes
- No code running.
- E-stop condition.
- Robot mode/state issue.

### Checks and fixes
1. Confirm Robot, Comm, and Code indicators are green.
2. Confirm E-stop is not active.
3. Verify robot is in expected mode (Teleop/Test/Auto).
4. Retry enable with safe robot positioning.

### If still failing
- Check console/log output for safety or runtime shutdown messages.

---

## Dashboard values not updating

### Symptoms
- Elastic Dashboard opens but values remain stale.

### Likely causes
- Code not publishing values.
- Lost robot communications.
- Wrong dashboard instance/session.

### Checks and fixes
1. Verify Driver Station comm/code are green.
2. Restart Elastic Dashboard.
3. Reboot robot code (redeploy if needed).
4. Confirm expected subsystem data updates after enable.

---

## Intermittent Wi-Fi or high packet loss

### Symptoms
- Driver Station comm drops in/out.
- High latency/packet loss warnings.

### Likely causes
- Weak signal/interference.
- Competing network adapters/routes.
- Unstable radio or power.

### Checks and fixes
1. Move laptop closer to robot field area access point.
2. Disable unused network interfaces (extra Wi-Fi/Ethernet/VPN).
3. Keep line-of-sight and reduce interference sources.
4. Check robot power stability first (brownout issues can look like comm issues).

---

## roboRIO appears unresponsive

### Symptoms
- No comm after full reboot and verified Wi-Fi connection.
- Deploy never detects target.

### Checks and fixes
1. Confirm robot is fully powered and breaker is on.
2. Wait enough time for full roboRIO boot.
3. Retry after complete power-cycle.
4. If issue persists repeatedly, escalate for hardware imaging/recovery workflow.

---

## Escalation package (what to collect)

When asking for help, provide:

1. Driver Station screenshot showing Robot/Comm/Code indicators.
2. Build or deploy terminal output (starting at first real error).
3. Description of what changed since last known-good run.
4. Whether issue reproduces on a second laptop or battery.
5. Any CAN/power observations (loose connectors, missing devices, brownout behavior).

This package usually cuts troubleshooting time significantly.
