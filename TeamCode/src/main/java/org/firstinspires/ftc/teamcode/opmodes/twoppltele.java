package org.firstinspires.ftc.teamcode.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

import org.firstinspires.ftc.teamcode.hardware.Drive;


import org.firstinspires.ftc.teamcode.hardware.intake.intake;
import org.firstinspires.ftc.teamcode.hardware.shooter.launcher;



@TeleOp(name="teletwo", group="Linear OpMode")
public class twoppltele extends LinearOpMode {


    // -- Hardware -- //
    private intake intake;
    private Drive drivetrain;
    private launcher shooter;

    public enum ShootBallState {
        //CHANGE SPECIMEN MODE
        START,
        LOAD_SHOOTER,
        SHOOT,
        IDLE


    }

    private final double speed = 1.0;
    ElapsedTime transferTime2 = new ElapsedTime();
    private ShootBallState ballstate;



    @Override
    public void runOpMode() {

        initDrive();



        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();
        shooter.setPitchPosition(0.5);


        waitForStart();
        ballstate = ShootBallState.IDLE;

//        intake.moveExtendo(0, 1);


        while (opModeIsActive() && !isStopRequested()) {

//            controllers.updateCopies(gamepad1, gamepad2);
//            controllers.readInputs();
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            // -- GAMEPAD 1 CONTROLS!!! --
            //start intake


            drivetrain.Speed = gamepad1.left_stick_y;  // Inverted forward/backward movement
            drivetrain.Strafe = gamepad1.left_stick_x;
            drivetrain.Turn = -gamepad1.right_stick_x * 0.4;  // Turning speed cut by 50%

            drivetrain.leftFrontPower = Range.clip(drivetrain.Speed - drivetrain.Strafe + drivetrain.Turn, -1, 1);
            drivetrain.rightFrontPower = Range.clip(drivetrain.Speed + drivetrain.Strafe - drivetrain.Turn, -1, 1);
            drivetrain.leftRearPower = Range.clip(drivetrain.Speed + drivetrain.Strafe + drivetrain.Turn, -1, 1);
            drivetrain.rightRearPower = Range.clip(drivetrain.Speed - drivetrain.Strafe - drivetrain.Turn, -1, 1);

            drivetrain.updateMotorPowers();
            //    ------------- GAMEPAD 2 CONTROLS ----------- //
            if (currentGamepad1.right_trigger > 0.5 && previousGamepad1.right_trigger > 0.5) {  // Change to gamepad2 if that's what you need.
                intake.setSpinner(1);
            } else if (currentGamepad1.left_trigger > 0.5 && previousGamepad1.left_trigger > 0.5) {  // Change to gamepad2 if that's what you need.
                intake.setSpinner(-1);
            } else {
                intake.setSpinner(0);
            }

            if (currentGamepad2.triangle && !previousGamepad2.triangle) {
                transferTime2.reset();// Change to gamepad2 if that's what you need.
              ballstate = ShootBallState.START;
            }


            if (currentGamepad1.left_bumper && !previousGamepad1.left_bumper) {  // Change to gamepad2 if that's what you need.
                intake.setTransferspeed(0);
            }
            if (currentGamepad1.right_bumper && !previousGamepad1.right_bumper) {  // Change to gamepad2 if that's what you need.
                intake.setTransferspeed(1);

            }
            if (currentGamepad1.rightBumperWasReleased()  || currentGamepad1.leftBumperWasReleased())
            {intake.setTransferspeed(0.5);
            }

            if (currentGamepad2.dpad_up && !previousGamepad2.dpad_up) {  // Change to gamepad2 if that's what you need.
                shooter.setPitchPosition(shooter.getPitchPosition()+0.005);
            }
            if (currentGamepad2.dpad_down && !previousGamepad2.dpad_down) {  // Change to gamepad2 if that's what you need.
                shooter.setPitchPosition(shooter.getPitchPosition()-0.005);
            }
            switch (ballstate) {
                case START:
                     // Change to gamepad2 if that's what you need.
                    shooter.setgecko(.9);

                   if (transferTime2.milliseconds() >= 3000){
                       ballstate = ShootBallState.LOAD_SHOOTER;
                   }
                    break;
                case LOAD_SHOOTER:

                    intake.setTransferspeed(.75);
                    ballstate = ShootBallState.SHOOT;
                    break;
                case SHOOT:
                    if (currentGamepad2.cross && !previousGamepad2.cross) {  // Change to gamepad2 if that's what you need.
                        shooter.setgecko(0);
                        intake.setTransferspeed(0.5);
                        ballstate = ShootBallState.IDLE;
                    }
                    break;

            }

            updateTelemetry();
        }

    }

    /**
     * Sends debug data to control hub
     */
    private void updateTelemetry () {
        telemetry.addData("Intake Data", 1);
        telemetry.addData("pitch_pos:", shooter.getPitchPosition());
        telemetry.addData("transferclock:", transferTime2.seconds());
        telemetry.addData("shootstate:", ballstate.name());

        // telemetry.addData("Spinner AMPS: ", intake.getSpinner().getCurrent(CurrentUnit.AMPS));

        telemetry.update();
    }


    /**
     * Call this once to initialize hardware when TeleOp initializes.
     */
    private void initDrive () {
//        this.controllers = new Controllers(this);

        this.intake = new intake();
        intake.init(hardwareMap);


        this.drivetrain = new Drive();
        drivetrain.init(hardwareMap);

        this.shooter = new launcher();
        shooter.init(hardwareMap);

    }

    public Drive getdrivetrain () {
        return drivetrain;
    }


    public intake getIntake () {
        return intake;
    }

    public launcher getShooter () {
        return shooter;
    }


}
