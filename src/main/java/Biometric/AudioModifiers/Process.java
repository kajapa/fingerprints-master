/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biometric.AudioModifiers;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Patryk
 */
public class Process {

    public List<double[]> SliceSignal(double[] a, int framesize) {
       
      
        List<double[]> Samples = new ArrayList<double[]>();
        double x[] = new double[framesize];

        for (int i = 0; i < a.length; i += 160) {
            int k=0;

            

            for (int j = i; j < i+framesize ; j++) {
                if (j < a.length) {
                    x[k] = a[j];
                   // System.out.printf("\n"+"Dodane obiekty "+ x[k]);
                   

                } else {
                    x[k] = 0;
                   //System.out.printf("\n"+"Poza a tablica "+ x[k]);
                }
                 k++;
                
            }


            Samples.add(x);

            //System.out.printf("\n"+"Element added: "+i);
            //Arrays.fill(x, 0);

        }

        return Samples;
    }
    public List<Complex[]> PowerSpectrum(List<double[]> frames){
    FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex result[] ;
        List<Complex[]> spectrum = new ArrayList<Complex[]>();

        for(double[] frame: frames){
            /*for(int i=0;i<frame.length;i++)
            {
                System.out.println("Frame "+ frame[i]);
            }*/
            result= fft.transform(frame, TransformType.FORWARD);
            /*for(int i=0;i<result.length;i++)
            {   if(result[i].getReal()>0&&result[i].getImaginary()>0)
                System.out.println("Real "+ result[i].getReal()+" Imag "+result[i].getImaginary());
            }*/
            spectrum.add(result);
    
    
    }
        return spectrum;
    
    
    }
    public List<double[]> ConvertFFTBin(double[] bank,double sampleRate,List<Complex[]> list)
    {
    double[] FFTBin= new double[bank.length];
    double res=0;
    List<double[]> score= new ArrayList<double[]>();

            double [] newbank=new double[bank.length];
                   for(Complex[] com :list)
                   {
                       for (int i = 0; i < bank.length; i++)
                       {
                           for (int j = 0; j < com.length; j++)
                           {

                               double[] filtered = new double[com.length];

                             // System.out.println("Real "+com[j].getReal()+"Imag"+com[j].getImaginary());

                               res = (Math.sqrt(Math.pow(com[j].getReal(), 2) + Math.pow(com[j].getImaginary(), 2)) * bank[i])/44100;
                               //if(res!=0)
                                  // System.out.println("Pierwiastek "+(Math.sqrt(Math.pow(com[j].getReal(), 2) + Math.pow(com[j].getImaginary(), 2))* bank[i])/44100);
                               newbank[i] += CosinusFunction(bank.length, res);

                           }
                          res = 0;
                       }

                       //FFTBin[i] = CosinusFunction(bank.length, res);
                       score.add(newbank);

                   }
        return score;  }






    public List<double[]> LogEnergy(List<double[]> bank){
        List<double[]> result= new ArrayList<double[]>();
       for(double[] table: bank)
       {
           double[] temp= new double[table.length];

           for(int i=0;i<table.length;i++){

               temp[i]=Math.log(table[i]);
           }
           result.add(temp);
       }


        return result;
    }

    
    public double HztoMel(double hz){
    
    return 2595*Math.log10(1+hz/700);
    }
    
    public double MeltoHz(double mel){
    
    return 700*(Math.pow(10,mel/2595.0)-1);
    }

    public double[] BytetoDoubleArray(byte[]a)
    {
        double[]result= new double[a.length];
        for(int i=0;i<a.length;i++)
        {
            result[i]=a[i];
        }

        return result;
    }

    public double CosinusFunction(int K,double n)
    {

        double pi=3.14;
        n=Math.log(n);
        double result=0;
    for(int i=0;i<K-1;i++)
    {
        result+=Math.cos(2*pi*(((2*i+1)*n)/4*K));
    }
   //
    return  result;

    }

}
