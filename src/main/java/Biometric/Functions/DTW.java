package Biometric.Functions;

import java.util.List;

public class DTW {

    public double Compare(List<double[]> a, List<double[]>b)
    {
        int xlen=a.size();
        int ylen=b.size();
        int count =0;
        double [][] D= new double[xlen][ylen];
        double result=0;
        for(int i=0;i<xlen;i++)
        {
             for(int j=0;j<ylen;j++)
             {
                 //System.out.println("A "+a.get(i)+" B " +b.get(j));
                if(i==0&&j==0)
                {

                    D[i][j]=Math.abs(GetDistance(a.get(i),b.get(j)));


                }
                else if(i==0&&j>0)
                {

                   D[i][j]=Math.abs(GetDistance(a.get(i),b.get(j)))+D[i][j-1];
                }
                else if(i>0&&j==0)
                {
                    D[i][j]=Math.abs(GetDistance(a.get(i),b.get(j)))+D[i-1][j];
                }
                else if(i>0&&j>0)
                    {
                    D[i][j]=Math.abs(GetDistance(a.get(i),b.get(j)))+ GetMin(D[i-1][j-1],D[i-1][j],D[i][j-1]);
                }

                // System.out.printf("\n" + "Element tablicy " + D[i][j]);

             }
        }

            for(int i=xlen-1;i>0;i--)
            {
                //System.out.printf("\n" + "Wynik przed " + result);
                for(int j=ylen-1;j>0;j--)
                {
                    if(i==xlen-1&&j==ylen-1)
                    {
                        result+=D[i][j];
                        count++;
                    }
                    else
                        {
                            //System.out.printf("\n" + "Dodane minimum " + GetMin(D[i-1][j-1],D[i-1][j],D[i][j-1]));
                            result+=GetMin(D[i-1][j-1],D[i-1][j],D[i][j-1]);
                            count++;
                        }
                   // System.out.printf("\n" + "Wynik po " + result);
                }

            }
        return result/count;
    }
public double GetMin(double a,double b,double c)
{       double [] temp= {a,b,c};
        double min =temp[0];

        for(int i=0;i<3;i++)
        {
            if(min>temp[i])
            {
                min=temp[i];

            }
        }


return min;
}
public double GetDistance(double[]a,double[]b)
{
    double sum=0;
    double result=0;
    for(int i=0;i<a.length;i++)
    {

        sum+=a[i]+b[i];
    }
    result=Math.sqrt(sum);


    return result;
}

}
