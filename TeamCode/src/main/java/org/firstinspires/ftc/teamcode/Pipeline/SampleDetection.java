package org.firstinspires.ftc.teamcode.Pipeline;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class SampleDetection extends OpenCvPipeline {
    private Telemetry telemetry;
    public SampleDetection(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        Mat h = new Mat(3, 3, CvType.CV_64F);

        double[][] hArray = {
                {1.45538818e+00,  4.74651118e-01, -1.39014816e+02},
                {-1.91056302e-02,  2.35917771e+00, -1.78695993e+02},
                {1.00860604e-04,  1.45801043e-03,  1.00000000e+00}
        };

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                h.put(row, col, hArray[row][col]);
            }
        }


        Mat warped = new Mat();
        Imgproc.warpPerspective(input, warped, h, input.size());


        Mat hsv = new Mat();
        Imgproc.cvtColor(warped, hsv, Imgproc.COLOR_RGB2HSV);



        Scalar yellowHighHSV = new Scalar(33, 255, 255);
        Scalar yellowLowHSV = new Scalar(17, 90, 90);
        Scalar redHighHSV1 = new Scalar(179, 240, 240);
        Scalar redLowHSV1 = new Scalar(150, 100, 100);
        Scalar redHighHSV2 = new Scalar(15, 240, 240);
        Scalar redLowHSV2 = new Scalar(0, 50, 100);
        Scalar blueHighHSV = new Scalar(140, 255, 255);
        Scalar blueLowHSV = new Scalar(100, 70, 50);

        Mat blueMask = new Mat();
        Mat redMask1 = new Mat();
        Mat redMask2 = new Mat();
        Mat yellowMask = new Mat();
        Core.inRange(hsv, blueLowHSV, blueHighHSV, blueMask);
        Core.inRange(hsv, yellowLowHSV, yellowHighHSV, yellowMask);
        Core.inRange(hsv, redLowHSV1, redHighHSV1, redMask1);
        Core.inRange(hsv, redLowHSV2, redHighHSV2, redMask2);

        Mat output = new    Mat();
        Core.bitwise_and(warped, warped, output, blueMask);
        Core.bitwise_and(warped, warped, output, yellowMask);
        Core.bitwise_and(warped, warped, output, redMask1);
        Core.bitwise_and(warped, warped, output, redMask2);



        Mat edges = new Mat();
        Imgproc.Canny(output, edges, 0, 0); //TODO: optimize threshold values

        Mat outedges = new Mat();
        Imgproc.dilate(edges, outedges, new Mat(3, 3, edges.type()));

        List<MatOfPoint> blobks = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(outedges, blobks, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_NONE);

        List<RotatedRect> brects = new ArrayList<>();
        for (int i = blobks.size() - 1; i >= 0; i--) {
            if (Imgproc.contourArea(blobks.get(i)) < 7000 || Imgproc.contourArea(blobks.get(i)) > 15000) { //TODO: change min and max contour size
                blobks.remove(i);
            } else {
                RotatedRect rotatedRect = Imgproc.minAreaRect(new MatOfPoint2f(blobks.get(i).toArray()));
                Point[] boxPoints = new Point[4];
                rotatedRect.points(boxPoints);
                brects.add(rotatedRect);
                blobks.set(i, new MatOfPoint(boxPoints));
            }
        }

        Mat contourImage = warped.clone();
        Imgproc.drawContours(contourImage, blobks, -1, new Scalar(0, 100, 100), 1);

        telemetry.addData("Contours Detected", blobks.size());
        for (RotatedRect b : brects) {
            telemetry.addData("Block" + brects.indexOf(b), b.angle);
        }
        for (MatOfPoint b : blobks) {
            Point[] points = b.toArray();
            double side1 = Math.sqrt(Math.pow(points[0].x - points[1].x, 2) + Math.pow(points[0].y - points[1].y, 2));
            double side2 = Math.sqrt(Math.pow(points[1].x - points[2].x, 2) + Math.pow(points[1].y - points[2].y, 2));
            double longSideLength = Math.max(side1, side2);
            Point[] longSide = side1 > side2 ? new Point[] {points[0], points[1]} : new Point[] {points[1], points[2]};
            Point lowerPoint = longSide[0].y > longSide[1].y ? longSide[0] : longSide[1];
            double angle = Math.toDegrees(Math.acos((Math.abs(longSide[0].x - longSide[1].x)) / longSideLength));
            telemetry.addData("angle", angle);
        }
        telemetry.update();
        return contourImage;
    }
}
