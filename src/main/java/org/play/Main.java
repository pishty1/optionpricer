package org.play;

import java.lang.*;
// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static double simpleMonteCarlo(double expiry, double strike, double spot, double vol, double r, long numberOfPaths) {
        double variance = vol * vol * expiry;
        double rootVariance = Math.sqrt(variance);
        double itoCorrection = -0.5 * variance;

        double movedSpot = spot * Math.exp(r*expiry + itoCorrection);
        double thisSpot;
        double runningSum = 0;

        for( long i=0; i< numberOfPaths; i++ ) {
            double thisGaussian = Rand1.GetGaussianByBoxMuller();
            thisSpot = movedSpot* Math.exp(rootVariance*thisGaussian);
            double thisPayOff = thisSpot - strike;
            thisPayOff = thisPayOff > 0 ? thisPayOff: 0;
            runningSum += thisPayOff;
        }

        double mean = runningSum/numberOfPaths;
        mean *= Math.exp(-r*expiry);
        return mean;
    }
    public static void main(String[] args) {
        // Press ⌥⏎ with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        double expiry = 0;

        double strike = 0;
        double spot = 0;
        double vol = 0;
        double r = 0;
        long numberOfPaths = 0;
        double result = simpleMonteCarlo(expiry, strike, spot, vol, r, numberOfPaths);
        System.out.printf("Hello and welcome! ".formatted(result));

        // Press ⌃R or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press ⌃D to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing ⌘F8.
            System.out.println("i = " + i);
        }
    }
}