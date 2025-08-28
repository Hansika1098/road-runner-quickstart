package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
//hello hihihi
@Autonomous
public class newstuff extends LinearOpMode{
    private ElapsedTime timer = new ElapsedTime();

    DcMotorEx frontLeftMotor;
    DcMotorEx backLeftMotor;
    DcMotorEx frontRightMotor;
    DcMotorEx backRightMotor;

    public void moveStraight(double power, int targetPos) {

        frontLeftMotor.setTargetPosition(targetPos);
        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLeftMotor.setPower(power);

        backLeftMotor.setTargetPosition(targetPos);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeftMotor.setPower(power);

        frontRightMotor.setTargetPosition(targetPos);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontRightMotor.setPower(power);

        backRightMotor.setTargetPosition(targetPos);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRightMotor.setPower(power);

        while(frontLeftMotor.isBusy() || frontRightMotor.isBusy() || backLeftMotor.isBusy() || backRightMotor.isBusy()) {

        }


    }

    public void strafe(double power, int targetPos) {

        frontLeftMotor.setTargetPosition(-targetPos);
        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLeftMotor.setPower(power);

        backLeftMotor.setTargetPosition(targetPos);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeftMotor.setPower(power);

        frontRightMotor.setTargetPosition(targetPos);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontRightMotor.setPower(power);

        backRightMotor.setTargetPosition(-targetPos);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRightMotor.setPower(power);

        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() || backLeftMotor.isBusy() || backRightMotor.isBusy()) {
        }

    }

    public void runOpMode() throws InterruptedException {

        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "fl");
        backLeftMotor  = hardwareMap.get(DcMotorEx.class, "bl");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "fr");
        backRightMotor  = hardwareMap.get(DcMotorEx.class, "br");

        frontLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);

        frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setTargetPosition(0);

        backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setTargetPosition(0);

        frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setTargetPosition(0);

        backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setTargetPosition(0);

        waitForStart();

        moveStraight(0.5, 500);
        frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);



        sleep(1000);

        strafe(0.5,500);
        sleep(1000);

        moveStraight(0.5,-500);
        sleep(1000);

        strafe(0.5,-500);
        sleep(1000);


        frontLeftMotor.setTargetPosition(-1000);

    }


}
