package org.play;

public class Rand1 {

    public static double GetGaussianBySummation() {
        double result  = 0;

        for(int i = 0; i < 12; i++) {
            result += Math.random();
        }

        result -= 6;
        return result;
    }

    public static double GetGaussianByBoxMuller() {
        double result;

        double x, y;

        double sizeSquared;

        do {
            x = 2.0 * Math.random()-1;
            y = 2.0 * Math.random()-1;
            sizeSquared = x*x + y*y;
        } while(sizeSquared >=1.0);

        result = x*Math.sqrt(-2*Math.log(sizeSquared)/sizeSquared);
        return result;

    }
}
