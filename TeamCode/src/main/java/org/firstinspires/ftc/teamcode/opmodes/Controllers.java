package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;
import java.util.Map;

//import hardware.intake.IntakeState;

public class Controllers {

    private final tele teleOp;

    private final Gamepad gamepadOne;
    private final Gamepad gamepadTwo;

    private final Gamepad previousGamepadOne = new Gamepad();
    private final Gamepad previousGamepadTwo = new Gamepad();

    private final Map<String, Runnable> padOneKeys = new HashMap<>();
    private final Map<String, Runnable> padTwoKeys = new HashMap<>();


    public Controllers(tele teleOp) {
        this.teleOp = teleOp;

        this.gamepadOne = teleOp.gamepad1;
        this.gamepadTwo = teleOp.gamepad2;

        setupKeyBinds();
    }

    /**
     * Updates the previous and current versions of controllers to avoid mis-inputs.
     * @param newPadOne most recently updated padOne
     * @param newPadTwo most recently updated padTwo
     */
    public void updateCopies(Gamepad newPadOne, Gamepad newPadTwo) {
        previousGamepadOne.copy(gamepadOne);
        previousGamepadTwo.copy(gamepadTwo);

        gamepadOne.copy(newPadOne);
        gamepadTwo.copy(newPadTwo);
    }

    public void readInputs() {

        if (gamepadOne.left_bumper && !previousGamepadOne.left_bumper) {
            padOneKeys.get("extendIntake").run();
        }

        if (gamepadOne.right_bumper && !previousGamepadOne.right_bumper) {
            padOneKeys.get("deliver").run();
        }

    }

    public void setupKeyBinds() {

        // ------------------------------ //
        // --------- Gamepad One -------- //
        // ------------------------------ //


//        padOneKeys.put("extendIntake", () -> {
//            if (teleOp.getIntake().getIntakeState().equals(IntakeState.IDLE)) {
//                teleOp.getIntake().setIntakeState(IntakeState.EXTENDING);
//            }
//        });
//
//        padOneKeys.put("deliver", () -> {
//            if (teleOp.getDelivery().getDeliveryState().equals(DeliveryState.READY)) {
//                teleOp.getDelivery().setDeliveryState(DeliveryState.DELIVER);
//            }
//        });










        // ----------------------------- //
        // -------- Gamepad Two -------- //
        // ----------------------------- //


    }









}
