package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.NonConst;


public class TestingAction extends LinearOpMode{

    ElapsedTime time = new ElapsedTime();
    public MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

    }


    @TeleOp
    public class MoveForward implements Action {
        private boolean init = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            if(!init) {
                time.reset();
                drive.rightFront.setPower(0.5);
                drive.leftFront.setPower(0.5);
                drive.rightBack.setPower(0.5);
                drive.leftBack.setPower(0.5);
                init = true;

            }
            if(time.milliseconds() < 3000) {
                return true;

            }
            drive.rightFront.setPower(0);
            drive.leftFront.setPower(0);
            drive.rightBack.setPower(0);
            drive.leftBack.setPower(0);
            return false;
        }

    }

}

