package org.firstinspires.ftc.teamcode.hardware.intake;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.HashMap;
import java.util.Map;

public class intake {


    // ---- Mechanisms ---- //
    private Servo transfer;
    private Servo stopper;
    private DcMotorEx extendo;
    private DcMotorEx spinner;
    ElapsedTime transferTime = new ElapsedTime();
    // ---- Sensors ---- //



    private boolean started = false;

    public void init(HardwareMap hwMap) {


       // this.wrist = hwMap.get(ServoImplEx.class, "intake_wrist");

        this.spinner = hwMap.get(DcMotorEx.class, "intake");
        this.transfer = hwMap.get(ServoImplEx.class, "transfer");
        this.spinner.setDirection(DcMotorEx.Direction.REVERSE);

//

    }





    public void setSpinner(double power) {
        spinner.setPower(power);
    }


    public void incrementWristPosition(double increment) {
        transfer.setPosition(transfer.getPosition() + increment);
    }

    public int getExtendoPosition(){
        return extendo.getCurrentPosition();
    }



    /**
     * Check if the intake is back at its original position by using the magnetic sensor
     * @return if the magnetic sensor is active
     */
    public void setTransferspeed(double position) {
        transfer.setPosition(position);
    }
    public void setTransferCyclein(double seconds) {
        transferTime.reset();
        if(transferTime.seconds() >= seconds){
        transfer.setPosition(1);
        } else {
            transfer.setPosition(0.5);
        }
    }
    public void setTransferCycleout(double seconds) {
        transferTime.reset();
        if(transferTime.seconds() >= seconds){
            transfer.setPosition(0);
        } else {
            transfer.setPosition(0.5);
        }
    }
    public Servo getTransfer() {
        return transfer;
    }
    public Servo getStopper(){
        return stopper;
    }





    public DcMotorEx getExtendo() {
        return extendo;
    }

    public DcMotorEx getSpinner() {
        return spinner;
    }



    /**
     * Check what type of sample that the intake color sensor detects
     * @return sample type (RED, YELLOW, BLUE)
     */


    public String getData() {

        StringBuilder data = new StringBuilder();






        return data.toString();
    }


    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public double getSpinnerVoltage() {
        return spinner.getCurrent(CurrentUnit.AMPS);
    }

    //    public Limelight getLimelight() {
//        return limelight;
//    }
    public String getIntakeAMPS(){
        return "Intake Slides Amps: " + String.format("%.2f", extendo.getCurrent(CurrentUnit.AMPS)) + "\n" +
                "Spinner Amps: " + String.format("%.2f", spinner.getCurrent(CurrentUnit.AMPS));
    }
}

