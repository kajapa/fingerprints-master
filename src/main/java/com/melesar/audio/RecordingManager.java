package com.melesar.audio;

import Biometric.Functions.DTW;
import Biometric.Main.Record;

import java.util.List;

public class RecordingManager
{
    private final String REFERENCE_NAME = "reference1.wav";
       private final String SAMPLE_NAME = "sample.wav";

    private final double SIMILARITY_THRESHOLD = 20;

    public boolean recordReference()
    {
        try {
            Record.Register(REFERENCE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public boolean record2ndsample()
    {
        try {
            Record.Register(REFERENCE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean compareSample()
    {
        try {
            Record.Register(SAMPLE_NAME);
            List<double[]> referenceData = Record.CaptureSound(REFERENCE_NAME);
            List<double[]> sampleData = Record.CaptureSound(SAMPLE_NAME);


            DTW dtw = new DTW();

            double similarity = dtw.Compare(referenceData, sampleData);
            System.out.printf("\n"+ "DTW SUM: "+similarity);

            return similarity <= SIMILARITY_THRESHOLD;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
