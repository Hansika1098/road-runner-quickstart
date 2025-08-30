package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
@Config
public class ArmTest extends LinearOpMode {

    public static int target = 0;
    public static double multiplier = 0.005;
    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx arm = hardwareMap.get(DcMotorEx.class, "arm");

        waitForStart();

        int distToTarget;

        distToTarget = target - arm.getCurrentPosition();

        int prevDist = distToTarget;
        while(opModeIsActive()){
            distToTarget = target - arm.getCurrentPosition();
            double p = distToTarget * multiplier;

            double d = (distToTarget - prevDist) /

            telemetry.addData("ArmPos", arm.getCurrentPosition());
            telemetry.update();
            prevDist = distToTarget;
        }




    }
}
