package org.play;

public class PayOff {

    private double strike;
    private OptionType optionType;

    public PayOff(double strike, OptionType optionType) {
        this.strike = strike;
        this.optionType = optionType;
    }

    public double operator(double spot) {
        return switch (optionType) {
            case put -> Math.max(spot - strike, 0);
            case call -> Math.max(strike - spot, 0);
        };
    }
}
