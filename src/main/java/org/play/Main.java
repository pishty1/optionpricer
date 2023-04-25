package org.play;

import java.lang.*;
// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static double simpleMonteCarlo(PayOff thePayOff, double expiry, double strike, double spot, double vol, double r, long numberOfPaths) {

        double variance = vol * vol * expiry;
        double rootVariance = Math.sqrt(variance);
        double itoCorrection = -0.5 * variance;
        double movedSpot = spot * Math.exp(r*expiry + itoCorrection);
        double thisSpot;
        double runningSum = 0;

        for( long i=0; i< numberOfPaths; i++ ) {
            double thisGaussian = Rand1.GetGaussianByBoxMuller();
            thisSpot = movedSpot* Math.exp(rootVariance*thisGaussian);
            double thisPayOff = thePayOff.operator(thisSpot);
            runningSum += thisPayOff;
        }

        double mean = runningSum/numberOfPaths;
        mean *= Math.exp(-r*expiry);
        return mean;
    }
    public static void main(String[] args) {

        double strike = 0;
        double expiry = 0;
        double spot = 0;
        double vol = 0;
        double r = 0;
        long numberOfPaths = 0;

        PayOff callPayOff = new PayOff(strike, OptionType.call);
        PayOff putPayOff = new PayOff(strike, OptionType.put);

        double resultCall = simpleMonteCarlo(callPayOff, expiry, strike, spot, vol, r, numberOfPaths);
        double resultPut  = simpleMonteCarlo(putPayOff, expiry, strike, spot, vol, r, numberOfPaths);

    }
}