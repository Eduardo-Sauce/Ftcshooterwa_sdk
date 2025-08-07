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



@TeleOp(name="tele", group="Linear OpMode")
public class tele extends LinearOpMode {


    // -- Hardware -- //
    private intake intake;
    private Drive drivetrain;
    private launcher shooter;

    private final double speed = 1.0;
    ElapsedTime transferTime2 = new ElapsedTime();





    @Override
    public void runOpMode() {

        initDrive();

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();
        shooter.setPitchPosition(0.5);


        waitForStart();


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

            if (currentGamepad1.triangle && !previousGamepad1.triangle) {  // Change to gamepad2 if that's what you need.
                shooter.setgecko(.9);
               sleep(3000);
                    intake.setTransferspeed(1);


            }
            else if (currentGamepad1.triangle && !previousGamepad1.triangle) {  // Change to gamepad2 if that's what you need.

            }
            if (currentGamepad1.cross && !previousGamepad1.cross) {  // Change to gamepad2 if that's what you need.
                shooter.setgecko(0);
                intake.setTransferspeed(0.5);
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

            if (currentGamepad1.dpad_up && !previousGamepad1.dpad_up) {  // Change to gamepad2 if that's what you need.
                shooter.setPitchPosition(shooter.getPitchPosition()+0.005);
            }
            if (currentGamepad1.dpad_down && !previousGamepad1.dpad_down) {  // Change to gamepad2 if that's what you need.
                shooter.setPitchPosition(shooter.getPitchPosition()-0.005);
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
