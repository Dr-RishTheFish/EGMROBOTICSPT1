// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets; 
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.SetShooterSpeed;
import frc.robot.commands.InputCommand;
import frc.robot.commands.OutputCommand;
import frc.robot.commands.RackandPinionCommandIn;
import frc.robot.commands.RackandPinionCommandOut;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.DashboardSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.RackandPinionSubsystem;
import edu.wpi.first.networktables.NetworkTableEntry;
import java.util.*;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final IndexerSubsystem m_indexerSubsystem = new IndexerSubsystem();
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  private final DashboardSubsystem m_dashboardSubsystem = new DashboardSubsystem(m_drivetrainSubsystem,
   m_shooterSubsystem, m_intakeSubsystem, m_indexerSubsystem, m_visionSubsystem);
  private final RackandPinionSubsystem m_rackandPinionSubsystem = new RackandPinionSubsystem();

  //Instantiating Xbox controller
  private static final XboxController controllerOne = new XboxController(0);
  public static final XboxController controllerTwo = new XboxController(1);

  
  //Controller one buttons
  JoystickButton aButton = new JoystickButton(controllerOne, 1);
  JoystickButton bButton = new JoystickButton(controllerOne, 2);
  JoystickButton xButton = new JoystickButton(controllerOne, 3);
  JoystickButton yButton = new JoystickButton(controllerOne, 4);
  JoystickButton leftBumperButton = new JoystickButton(controllerOne, 5);
  JoystickButton rightBumperButton = new JoystickButton(controllerOne, 6);
  JoystickButton leftTinyButton = new JoystickButton(controllerOne, 7);
  JoystickButton rightTinyButton = new JoystickButton(controllerOne, 8);
  JoystickButton leftJoystickButton = new JoystickButton(controllerOne, 9);
  JoystickButton rightJoystickButton = new JoystickButton(controllerOne, 10);

 //Controller two buttons
  JoystickButton aButton2 = new JoystickButton(controllerTwo, 1);
  JoystickButton bButton2 = new JoystickButton(controllerTwo, 2);
  JoystickButton xButton2 = new JoystickButton(controllerTwo, 3);
  JoystickButton yButton2 = new JoystickButton(controllerTwo, 4);
  JoystickButton leftBumperButton2 = new JoystickButton(controllerTwo, 5);
  JoystickButton rightBumperButton2 = new JoystickButton(controllerTwo, 6);
  JoystickButton leftTinyButton2 = new JoystickButton(controllerTwo, 7);
  JoystickButton rightTinyButton2 = new JoystickButton(controllerTwo, 8);
  JoystickButton leftJoystickButton2 = new JoystickButton(controllerTwo, 9);
  JoystickButton rightJoystickButton2 = new JoystickButton(controllerTwo, 10);


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation

    //reset motors

    m_shooterSubsystem.shooter_left.configFactoryDefault(); 
    m_intakeSubsystem.intake.configFactoryDefault();
    m_indexerSubsystem.indexer_left.configFactoryDefault();
    m_indexerSubsystem.indexer_right.configFactoryDefault();
    m_indexerSubsystem.indexer_right.configFactoryDefault();

    m_indexerSubsystem.indexer_right.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 1, 1, 0.1));
    //m_shooterSubsystem.shooter_left.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 5, 25, 0.1));
    m_rackandPinionSubsystem.rack_and_pinion.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 20, 35, 0.1)); 


    //driver subsystem 
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
            m_drivetrainSubsystem,
            () -> -modifyAxis(controllerOne.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(controllerOne.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(controllerOne.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));

    // Configure the button bindings
    configureButtonBindings();
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Back button zeros the gyroscope
    new Button(controllerOne::getBackButton)
            // No requirements because we don't need to interrupt anything
            .whenPressed(m_drivetrainSubsystem::zeroGyroscope);

    // TODO: fix. Need button binding to fire command 
    //new Button(m_controller::getAButton)
      //.whenHeld(m_shooterSubsystem::periodic);

      xButton2.whenHeld(new ShootCommand(m_shooterSubsystem, m_indexerSubsystem)); 
      yButton2.whenHeld(new SetShooterSpeed(m_shooterSubsystem));
      rightBumperButton.whenHeld(new IntakeCommand(m_intakeSubsystem)); 
      rightBumperButton2.whenHeld(new InputCommand(m_indexerSubsystem)); 
      leftBumperButton2.whenHeld(new OutputCommand(m_indexerSubsystem)); 
      bButton.whenHeld(new RackandPinionCommandIn(m_rackandPinionSubsystem));
      xButton.whenHeld(new RackandPinionCommandOut(m_rackandPinionSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.05);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
    
  }
}
