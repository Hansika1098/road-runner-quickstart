package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.ejml.dense.row.decomposition.qr.QRColPivDecompositionHouseholderColumn_DDRM;

@TeleOp
@Config
public class ArmTestOG extends LinearOpMode {

    public static int target = 0;
    public static double multiplier = 0.005;
    private ElapsedTime timer = new ElapsedTime();

    double ticksPerDegrees = 2.0;

    public static double p = 0, i = 0,  d = 0, f = 0;

    public PIDController controller  = new PIDController(p,i,d);



    @Override


    public void runOpMode() throws InterruptedException {
        DcMotorEx arm = hardwareMap.get(DcMotorEx.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();



        while(opModeIsActive()){
            controller.setPID(p,i,d);
            double pid = controller.calculate(arm.getCurrentPosition(), target);
            double ff = Math.cos(Math.toRadians(target / ticksPerDegrees))*f;

            arm.setPower(pid + ff);

            telemetry.addData("ArmPos", arm.getCurrentPosition());
            telemetry.update();



        }




    }
}
