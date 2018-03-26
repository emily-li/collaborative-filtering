package com.liemily.recommender.example.math;

import static java.lang.Math.sqrt;

public class RMSECalculator extends ErrorCalculator {

    @Override
    double getError(final double[] prediction, final double[] actual) {
        final double mse = meanSquaredError(prediction, actual);
        return sqrt(mse);
    }

    double meanSquaredError(double[] prediction, double[] actual) {
        return 0;
    }
}
