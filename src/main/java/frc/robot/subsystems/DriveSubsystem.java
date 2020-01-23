package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
	private final TalonSRX m_masterLeft = new TalonSRX(DriveConstants.kMasterLeft);
	private final TalonSRX m_followerLeft1 = new TalonSRX(DriveConstants.kFollowerLeft1);
	private final TalonSRX m_followerLeft2 = new TalonSRX(DriveConstants.kFollowerLeft2);
	private final TalonSRX m_masterRight = new TalonSRX(DriveConstants.kMasterRight);
	private final TalonSRX m_followerRight1 = new TalonSRX(DriveConstants.kFollowerRight1);
	private final TalonSRX m_followerRight2 = new TalonSRX(DriveConstants.kFollowerRight2);

	/**
	 * Initializes a new instance of the {@link DriveSubsystem} class.
	 */
	public DriveSubsystem() {
		m_masterLeft.setInverted(false);
		m_masterLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 1000);
		m_masterLeft.configClosedloopRamp(0.1, 1000);
		m_masterLeft.configOpenloopRamp(0.1, 1000);
		m_masterLeft.enableVoltageCompensation(true);
		m_masterLeft.setSensorPhase(true);
		m_masterLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 1000);
		m_masterLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 1000);
		m_masterLeft.configNominalOutputForward(0, 1000);
		m_masterLeft.configNominalOutputReverse(0, 1000);
		m_masterLeft.configPeakOutputForward(1, 1000);
		m_masterLeft.configPeakOutputReverse(-1, 1000);
		m_masterLeft.selectProfileSlot(0, 0);
		m_masterLeft.config_kF(0, 0.08189, 1000);
		m_masterLeft.config_kP(0, 0.5, 1000);
		m_masterLeft.config_kI(0, 0.004, 1000);
		m_masterLeft.config_kD(0, 1.5, 1000);
		m_masterLeft.config_IntegralZone(0, 100, 1000);
		m_masterLeft.configMotionCruiseVelocity(9370, 1000);
		m_masterLeft.configMotionAcceleration(9370, 1000);
		m_masterLeft.setSelectedSensorPosition(0, 0, 1000);

		m_followerLeft1.follow(m_masterLeft);
		m_followerLeft1.setInverted(true);

		m_followerLeft2.follow(m_masterLeft);
		m_followerLeft2.setInverted(true);

		m_masterRight.setInverted(true);
		m_masterRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 1000);
		m_masterRight.configClosedloopRamp(0.1, 1000);
		m_masterRight.configOpenloopRamp(0.1, 1000);
		m_masterRight.enableVoltageCompensation(true);
		m_masterRight.setSensorPhase(false);
		m_masterRight.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 1000);
		m_masterRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 1000);
		m_masterRight.configNominalOutputForward(0, 1000);
		m_masterRight.configNominalOutputReverse(0, 1000);
		m_masterRight.configPeakOutputForward(1, 1000);
		m_masterRight.configPeakOutputReverse(-1, 1000);
		m_masterRight.selectProfileSlot(0, 0);
		m_masterRight.config_kF(0, 0.08189, 1000);
		m_masterRight.config_kP(0, 0.5, 1000);
		m_masterRight.config_kI(0, 0.004, 1000);
		m_masterRight.config_kD(0, 1.5, 1000);
		m_masterRight.config_IntegralZone(0, 100, 1000);
		m_masterRight.configMotionCruiseVelocity(9370, 1000);
		m_masterRight.configMotionAcceleration(9370, 1000);
		m_masterRight.setSelectedSensorPosition(0, 0, 1000);

		m_followerRight1.follow(m_masterRight);
		m_followerLeft1.setInverted(true);

		m_followerRight2.follow(m_masterRight);
		m_followerRight2.setInverted(true);
	}

    public void setLeftSpeed(double speed) { 
        m_masterLeft.set(ControlMode.PercentOutput, -speed);
    }

    public void setRightSpeed(double speed) {
        m_masterRight.set(ControlMode.PercentOutput, speed);
	}

	public void arcadeDrive(double speed, double left, double right) {
		setLeftSpeed(speed - left + right);
		setRightSpeed(speed - right + left);
	}
}