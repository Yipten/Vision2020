package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArduinoSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class TargetCommand extends CommandBase {
    private final DriveSubsystem m_driveSubsystem;
    private final ArduinoSubsystem m_arduinoSubsystem;

    /**
     * Initializes a new instance of the {@link TargetCommand} class.
     * 
     * @param driveSubsystem   {@link DriveSubsystem} to be used.
     * @param arduinoSubsystem {@link ArduinoSubsystem} to be used.
     */
    public TargetCommand(DriveSubsystem driveSubsystem, ArduinoSubsystem arduinoSubsystem) {
        m_driveSubsystem = driveSubsystem;
        m_arduinoSubsystem = arduinoSubsystem;

        addRequirements(m_driveSubsystem, m_arduinoSubsystem);
    }

    @Override
    public void execute() {
        // read and write Arduino data
        m_arduinoSubsystem.update();
        // if target is in the camera's view
        if (m_arduinoSubsystem.getTargetInView())
            // drive based on Arduino data
            m_driveSubsystem.arcadeDrive(-m_arduinoSubsystem.getDriveSpeed(), m_arduinoSubsystem.getTurnSpeed(),    // TODO: use Math.max() to make min speed of 0.4
                    -m_arduinoSubsystem.getTurnSpeed());
        else
            m_driveSubsystem.arcadeDrive(0.0, 0.0, 0.0);
    }
}