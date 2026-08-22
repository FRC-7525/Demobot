# Demobot Operations Guide

Use this guide for normal daily robot startup, operation, and shutdown.

## Safety first

- Keep robot on blocks or in a clearly safe area before enabling.
- Keep people clear of moving mechanisms.
- Keep one operator focused on Driver Station state.
- Be ready to disable immediately if behavior is unexpected.

## Daily startup checklist

1. Install charged battery and verify Anderson connectors are fully seated.
2. Turn on robot main breaker.
3. Connect laptop to **Demobot** Wi-Fi.
4. Open **FRC Driver Station** and verify Robot/Comm/Code status.
5. Open dashboard and verify telemetry updates.

## Pre-enable checks

Before enabling, confirm:
- Driver Station has stable green indicators.
- Correct robot code is deployed.
- No obvious CAN or power faults are active.
- Field area is safe and clear.

## Enable flow (practice workflow)

1. Select the desired mode in Driver Station (Teleop/Test/Auto).
2. Announce “enabling” to everyone nearby.
3. Enable robot.
4. Verify expected subsystem behavior.
5. If anything unexpected occurs, disable immediately.

## During operation

- Monitor Driver Station for communication, code, and battery warnings.
- Watch for brownout signs (resets, comm drops, subsystem restarts).
- If controls do not respond, disable and verify joystick mapping first.

## Disable and shutdown

1. Disable robot in Driver Station.
2. Wait for all mechanisms to stop.
3. Turn off main breaker.
4. Disconnect battery if the robot is being stored or worked on.
5. Close Driver Station and WPILib tools.

## Between-match quick reset

If behavior is unstable between runs:
1. Disable robot.
2. Power-cycle robot once.
3. Recheck Comm/Code indicators.
4. Re-enable only after stability returns.

## Escalation trigger points

Escalate quickly when any of these occur repeatedly:
- Robot comm cannot be maintained.
- Deploy repeatedly fails.
- Frequent brownouts or random resets.
- CAN devices repeatedly disappear.

Use the [Troubleshooting Guide](./troubleshooting.md) for targeted recovery steps.
