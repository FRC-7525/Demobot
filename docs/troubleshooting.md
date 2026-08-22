# Demobot Troubleshooting Guide

Use this guide when setup, build, deploy, or robot connection steps fail.

## Quick triage order

1. Verify robot power (battery, breaker, Anderson connector seating).
2. Verify laptop Wi-Fi connection to the robot network.
3. Verify Driver Station communication status.
4. Verify code build status in WPILib VS Code.
5. Verify deploy status and roboRIO reachability.

## Common issues

## Driver Station not connecting

### Symptoms
- Driver Station shows red or yellow communication indicators.

### Checks
- Confirm laptop is connected to the expected robot network.
- Restart Driver Station.
- Power-cycle the robot.
- Confirm the robot has finished booting before reconnect attempts.

### Picture placeholder
Driver Station window showing disconnected state and where to check indicator lights.

---

## Build fails in WPILib

### Symptoms
- `WPILib: Build Robot Code` reports failure.

### Checks
- Read the first real error in terminal output (not just summary lines).
- Make sure WPILib version matches project requirements.
- Re-run the build after reopening WPILib VS Code.

### Picture placeholder
WPILib terminal output highlighting where the first build error appears.

---

## Deploy hangs or fails

### Symptoms
- Deploy takes unusually long or exits with an error.

### Checks
- Confirm Driver Station can see robot communications first.
- Cancel deploy once and retry.
- Reboot robot and retry deploy.
- Confirm no firewall or network profile is blocking local robot traffic.

### Picture placeholder
VS Code terminal showing a deploy timeout/failure and a successful retry.

---

## Robot has power but no code status

### Symptoms
- Robot powers on but Driver Station code indicator does not go green.

### Checks
- Rebuild and redeploy code.
- Check roboRIO logs for startup errors.
- Confirm the project deployed is this repository’s code.

### Picture placeholder
Driver Station panel showing code indicator state and where to confirm it.

---

## CAN-related behavior (WAGO loop, SPARK MAX, NEO)

### Symptoms
- CAN warnings, missing devices, or intermittent motor controller responses.

### Checks
- Inspect WAGO CAN loop continuity end-to-end.
- Confirm each SPARK MAX appears with expected ID.
- Check for loose or damaged CAN connectors/wiring.
- Verify terminations and polarity are correct.

### Picture placeholder
Annotated photo of the CAN wiring path through WAGO, SPARK MAX controllers, and endpoints.

---

## Power distribution issues (PDH / Anderson)

### Symptoms
- Brownouts, random reboots, or intermittent subsystem resets.

### Checks
- Confirm battery is charged and healthy.
- Check Anderson connectors are fully seated and not heat-damaged.
- Verify PDH connections are tight and correctly landed.
- Check for signs of voltage drop under load.

### Picture placeholder
Close-up of PDH and Anderson connectors showing correct seating and wiring layout.

---

## If nothing works

1. Restart laptop and robot.
2. Reconnect Wi-Fi and reopen Driver Station.
3. Build and deploy again.
4. Escalate with:
   - Driver Station screenshot
   - Build/deploy terminal output
   - What changed since the last known good run
