package Biometric.Functions;

import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DCT {

    public  List<double[]> transform(List<double[]> input) {
        List<double[]> result = new ArrayList<double[]>();
        //double[] vector;

        for(double[] table:input)
        {
            Objects.requireNonNull(table);
        Complex[] temp;
        int len = table.length;
        int halfLen = len / 2;
        double[] real = new double[len];
        for (int i = 0; i < halfLen; i++) {
            real[i] = table[i * 2];
            real[len - 1 - i] = table[i * 2 + 1];
        }
        if (len % 2 == 1)
            real[halfLen] = table[len - 1];
        Arrays.fill(table, 0.0);
        FFT.fft(real, table, true);


        for (int i = 0; i < len; i++) {
            double tem = i * Math.PI / (len * 2);
            double element = real[i] * Math.cos(tem) + table[i] * Math.sin(tem);
            if (element > 2 && element < 13) {
                table[i] = element;
            } else {
                table[i] = 0;
            }

        }
        result.add(table);
    }
    return  result;}


}
