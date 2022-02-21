
package frc.robot;

import frc.robot.Other.ManualOther;
import frc.robot.commands.*;
import frc.robot.commands.Climb.LockClimber;
import frc.robot.commands.Climb.LowerClimber;
import frc.robot.commands.Climb.ManuelClimber;
import frc.robot.commands.Climb.UnlockClimber;
// import frc.robot.commands.Climb.UnlockTraverse;
import frc.robot.commands.Drive.ManualDrive;
import frc.robot.commands.Drive.ReverseDrive;
import frc.robot.commands.Drive.TurnByAngle;
import frc.robot.commands.Drive.moveFoward;
import frc.robot.commands.Vision.UpdateTargets;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot
 * (including subsystems, commands, and button mappings) should be declared
 * here.
 */
public class RobotContainer {

  public final static boolean TESTING_CLIMBER = false;

  private static RobotContainer m_robotContainer = new RobotContainer();

  // The robot's subsystems
  //public final OtherSystem m_otherSystem = new OtherSystem();
  public DriveSystem m_driveSystem;
  public final VisionSystem m_visionSystem = new VisionSystem();
  public ClimbSystem m_climbSystem;

  // Joysticks
  private final XboxController assistController = new XboxController(1);
  private final XboxController driverController = new XboxController(0);

  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  private RobotContainer() {

    if (TESTING_CLIMBER) {
      m_climbSystem = new ClimbSystem();
    } else {
      m_driveSystem = new DriveSystem();
    }
    // Smartdashboard Subsystems

    // SmartDashboard Buttons
    if (TESTING_CLIMBER) {
      SmartDashboard.putData("LockClimber", new LockClimber(m_climbSystem));
      SmartDashboard.putData("RaiseClimber", new ManuelClimber(m_climbSystem));
      SmartDashboard.putData("LowerClimber", new LowerClimber(m_climbSystem));
    }

    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    if (!TESTING_CLIMBER) {
      SmartDashboard.putData("ManualDrive", new ManualDrive(m_driveSystem));
    }

    //SmartDashboard.putData("ManualServo", new ManualServo(m_otherSystem));
    //SmartDashboard.putData("ManualSparkMotor", new ManualSparkMotor(m_otherSystem));

    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    if (!TESTING_CLIMBER) {
      m_driveSystem.setDefaultCommand(new ManualDrive(m_driveSystem));
    }
   
    //m_otherSystem.setDefaultCommand(new ManualOther(m_otherSystem));
    //m_visionSystem.setDefaultCommand(new UpdateTargets(m_visionSystem));
    // Configure autonomous sendable chooser

    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  public XboxController getdriverController() {
    return driverController;
  }

  public XboxController getassistController() {
    return assistController;
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
    // Create some buttons
    final JoystickButton templateBtn = new JoystickButton(driverController, XboxController.Button.kA.value);
    templateBtn.whenPressed(new AutonomousCommand(), true);
    SmartDashboard.putData("TemplateBtn", new AutonomousCommand());

    final JoystickButton moveForwardBtn = new JoystickButton(assistController, XboxController.Button.kB.value);
    if (!TESTING_CLIMBER) {
      moveForwardBtn.whenPressed(new moveFoward(m_driveSystem, 100, 0.25), true);
      SmartDashboard.putData("moveForwardBtn", new moveFoward(m_driveSystem, 100, 0.25));
    }

    final JoystickButton turnByAngleBtn = new JoystickButton(driverController, XboxController.Button.kX.value);
    turnByAngleBtn.whenPressed(new TurnByAngle(m_driveSystem, 30));
    SmartDashboard.putData("turnByAngleBtn", new TurnByAngle(m_driveSystem, 30));

    final JoystickButton reverseDriveBtn = new JoystickButton(driverController, XboxController.Button.kBack.value);
    if (!TESTING_CLIMBER) {
      reverseDriveBtn.whenPressed(new ReverseDrive(m_driveSystem), true);
      SmartDashboard.putData("reverseDriveBtn", new ReverseDrive(m_driveSystem));
    }

    final JoystickButton lockClimberBtn = new JoystickButton(driverController, XboxController.Button.kX.value);
    if (TESTING_CLIMBER) {
      lockClimberBtn.whenPressed(new LockClimber(m_climbSystem), true);
      SmartDashboard.putData("lockClimberBtn", new LockClimber(m_climbSystem));
    }

    final JoystickButton unlockClimberBtn = new JoystickButton(driverController, XboxController.Button.kA.value);
    if (TESTING_CLIMBER) {
      unlockClimberBtn.whenPressed(new UnlockClimber(m_climbSystem), true);
      SmartDashboard.putData("unlockClimberBtn", new UnlockClimber(m_climbSystem));
    }

    final JoystickButton raiseClimberBtn = new JoystickButton(assistController, XboxController.Button.kA.value);
    if (TESTING_CLIMBER) {
      raiseClimberBtn.whenPressed(new ManuelClimber(m_climbSystem), true);
      SmartDashboard.putData("raiseClimberBtn", new ManuelClimber(m_climbSystem));
    }

    final JoystickButton lowerClimberBtn = new JoystickButton(assistController, XboxController.Button.kA.value);
    if (TESTING_CLIMBER) {
      lowerClimberBtn.whenPressed(new LowerClimber(m_climbSystem), true);
      SmartDashboard.putData("lowerClimberBtn", new LowerClimber(m_climbSystem));
    }

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }

}
