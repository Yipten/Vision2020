package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {
    private final DriveSubsystem m_driveSubsystem;
    private final Supplier<Double> m_straightSpeed;
    private final Supplier<Double> m_leftSpeed;
    private final Supplier<Double> m_rightSpeed;

    /**
     * Initializes a new instance of the {@link DriveCommand} class.
     * 
     * @param driveSubsystem {@link DriveSubsystem} to be used.
     * @param straightSpeed  Supplier of driving speed.
     * @param leftSpeed      Supplier of left turn speed.
     * @param rightSpeed     Supplier of right turn speed.
     */
    public DriveCommand(DriveSubsystem driveSubsystem, Supplier<Double> straightSpeed, Supplier<Double> leftSpeed,
            Supplier<Double> rightSpeed) {
        m_driveSubsystem = driveSubsystem;
        m_straightSpeed = straightSpeed;
        m_leftSpeed = leftSpeed;
        m_rightSpeed = rightSpeed;

        addRequirements(m_driveSubsystem);
    }

    @Override
    public void execute() {
        m_driveSubsystem.arcadeDrive(m_straightSpeed.get(), m_leftSpeed.get(), m_rightSpeed.get());
    }
}