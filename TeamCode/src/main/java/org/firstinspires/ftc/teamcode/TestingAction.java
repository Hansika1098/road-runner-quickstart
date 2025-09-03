package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.NonConst;

import java.util.*;

@TeleOp
public class TestingAction extends LinearOpMode{
    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    ElapsedTime time = new ElapsedTime();
    public MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

    }


    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));

        waitForStart();


        while(opModeIsActive()) {
            TelemetryPacket packet = new TelemetryPacket();

            if (gamepad1.a) {
                runningActions.add(MoveForward());
            }

            // updated based on gamepads

            // update running actions
            List<Action> newActions = new ArrayList<>();
            for (Action action : runningActions) {
                action.preview(packet.fieldOverlay());
                if (action.run(packet)) {
                    newActions.add(action);
                }
            }
            runningActions = newActions;

            dash.sendTelemetryPacket(packet);

        }


    }


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

    public Action MoveForward() {return new MoveForward(); }

}

