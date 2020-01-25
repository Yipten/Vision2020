package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.TargetCommand;
import frc.robot.subsystems.ArduinoSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {
	// subsystems
	private final ArduinoSubsystem m_arduinoSubsystem = new ArduinoSubsystem();
	private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
	// controllers
	private final Joystick m_driverController = new Joystick(ControllerConstants.kDriverControllerPort);

	/**
	 * Initializes a new instance of the {@link RobotContainer} class.
	 */
	public RobotContainer() {
		configureButtonBindings();

		m_driveSubsystem.setDefaultCommand(new DriveCommand(m_driveSubsystem,
				() -> -m_driverController.getRawAxis(ControllerConstants.Axis.kLeftY),
				() -> (m_driverController.getRawAxis(ControllerConstants.Axis.kLeftTrigger) + 1) / 2,
				() -> (m_driverController.getRawAxis(ControllerConstants.Axis.kRightTrigger) + 1) / 2));
	}

	/**
	 * Binds controller buttons and triggers to commands.
	 */
	private void configureButtonBindings() {
		new JoystickButton(m_driverController, ControllerConstants.Button.kTriangle)
				.whenHeld(new TargetCommand(m_driveSubsystem, m_arduinoSubsystem));
	}
}
