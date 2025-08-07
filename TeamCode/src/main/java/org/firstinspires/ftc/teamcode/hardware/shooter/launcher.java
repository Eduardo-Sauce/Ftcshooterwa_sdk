package org.firstinspires.ftc.teamcode.hardware.shooter;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class launcher {


    // ---- Mechanisms ---- //
    private ServoImplEx pitch;
    private DcMotorEx gecko;

    // ---- Sensors ---- //

    public void init(HardwareMap hwMap) {

        this.gecko = hwMap.get(DcMotorEx.class, "shooter");
        this.pitch = hwMap.get(ServoImplEx.class, "pitch");

        this.gecko.setDirection(DcMotorEx.Direction.FORWARD);

//

    }


    public void setgecko(double power) {gecko.setPower(power);}

    public void setPitchPosition(double position) {
        pitch.setPosition(position);
    }

    public double getPitchPosition() {
        return pitch.getPosition();
    }
    public DcMotorEx getGecko() {
        return gecko;
    }


    public String getData() {

        StringBuilder data = new StringBuilder();


        return data.toString();
    }
}