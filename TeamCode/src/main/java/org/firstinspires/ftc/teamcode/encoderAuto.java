package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
//hello hihihi
@Autonomous
public class encoderAuto extends LinearOpMode{
    private ElapsedTime timer = new ElapsedTime();

    public void runOpMode() throws InterruptedException {
        DcMotorEx frontLeftMotor = hardwareMap.get(DcMotorEx.class, "fl");
        DcMotorEx backLeftMotor = hardwareMap.get(DcMotorEx.class, "bl");
        DcMotorEx frontRightMotor = hardwareMap.get(DcMotorEx.class, "fr");
        DcMotorEx backRightMotor = hardwareMap.get(DcMotorEx.class, "br");



        frontLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorEx.Direction.REVERSE);



        waitForStart();



        //strafing left
        frontLeftMotor.setPower(-0.25);
        frontRightMotor.setPower(0.25);
        backLeftMotor.setPower(0.25);
        backRightMotor.setPower(-0.25);

        //sleep(1000);
        timer.reset();

        //this loop stops until time turns to one second (1000 milliseconds)
        while(timer.milliseconds() < 1000) {

        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        timer.reset();


        while(timer.milliseconds() < 1000) {

        }


        //go forward

        frontLeftMotor.setPower(0.25);
        frontRightMotor.setPower(0.25);
        backLeftMotor.setPower(0.25);
        backRightMotor.setPower(0.25);

        //sleep(1000);
        timer.reset();

        //this loop stops until time turns to one second (1000 milliseconds)
        while(timer.milliseconds() < 1000) {

        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);



        timer.reset();


        while(timer.milliseconds() < 1000) {

        }
        //strafing right

        frontLeftMotor.setPower(0.25);
        frontRightMotor.setPower(-0.25);
        backLeftMotor.setPower(-0.25);
        backRightMotor.setPower(0.25);

        //sleep(1000);
        timer.reset();

        //this loop stops until time turns to one second (1000 milliseconds)
        while(timer.milliseconds() < 1000) {

        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        timer.reset();


        while(timer.milliseconds() < 1000) {

        }


        //go backwards

        frontLeftMotor.setPower(-0.25);
        frontRightMotor.setPower(-0.25);
        backLeftMotor.setPower(-0.25);
        backRightMotor.setPower(-0.25);

        //sleep(1000);
        timer.reset();

        //this loop stops until time turns to one second (1000 milliseconds)
        while(timer.milliseconds() < 1000) {

        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);












    }

    ;

}
