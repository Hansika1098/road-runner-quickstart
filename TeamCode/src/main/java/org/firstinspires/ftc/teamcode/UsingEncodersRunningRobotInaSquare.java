package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
//hello hihihi
@Autonomous
public class UsingEncodersRunningRobotInaSquare extends LinearOpMode{
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

    public void resetEncoders() {
        frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runOpMode() throws InterruptedException {

        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "fl");
        backLeftMotor  = hardwareMap.get(DcMotorEx.class, "bl");
        frontRightMotor = hardwareMap.get(DcMotorEx.class, "fr");
        backRightMotor  = hardwareMap.get(DcMotorEx.class, "br");

        frontLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);

        frontLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        backLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        frontRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        backRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        moveStraight(0.5, 500);
        resetEncoders();
        timer.reset();
        while(timer.milliseconds() < 1000) {
        }

        strafe(0.5,500);
        resetEncoders();
        timer.reset();
        while(timer.milliseconds() < 1000) {
        }

        moveStraight(0.5,-500);
        resetEncoders();
        timer.reset();
        while(timer.milliseconds() < 1000) {
        }

        strafe(0.5,-500);
        resetEncoders();
        timer.reset();
        while(timer.milliseconds() < 1000) {
        }


        frontLeftMotor.setTargetPosition(-1000);

    }


}
