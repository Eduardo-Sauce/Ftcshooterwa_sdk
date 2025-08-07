package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Drive {

    private DcMotorEx leftFront;
    private DcMotorEx rightFront;
    private DcMotorEx leftRear;
    private DcMotorEx rightRear;
    public double Speed, Strafe, Turn;
    public double leftFrontPower, leftRearPower, rightFrontPower, rightRearPower;

    public void init(HardwareMap hardwareMap) {
        this.leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        this.leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        this.rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        this.rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void updateMotorPowers() {
        leftFront.setPower(leftFrontPower);
        rightFront.setPower(rightFrontPower);
        leftRear.setPower(leftRearPower);
        rightRear.setPower(rightRearPower);
    }




    public DcMotor getRightRear() {
        return rightRear;
    }

    public DcMotor getLeftRear() {
        return leftRear;
    }

    public DcMotor getRightFront() {
        return rightFront;
    }

    public DcMotor getLeftFront() {
        return leftFront;
    }

    public String getDriveTrainAMPS(){
        return "Delivery leftFron Amps: " + String.format("%.2f", leftFront.getCurrent(CurrentUnit.AMPS)) + "\n" +
                "Delivery rightFron Amps: " + String.format("%.2f", rightFront.getCurrent(CurrentUnit.AMPS)) + "\n" +
                "Delivery leftRear Amps: " + String.format("%.2f", leftRear.getCurrent(CurrentUnit.AMPS)) + "\n" +
                "Delivery rightRear Amps: " + String.format("%.2f", rightRear.getCurrent(CurrentUnit.AMPS)) + "\n";
    }

}
