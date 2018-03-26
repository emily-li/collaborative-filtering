package com.liemily.recommender.example.math;

import java.util.Arrays;

import static java.lang.Math.sqrt;

public class RMSECalculator extends ErrorCalculator {

    @Override
    double getError(final double[] prediction, final double[] actual) {
        final double mse = meanSquaredError(prediction, actual);
        return sqrt(mse);
    }

    double meanSquaredError(final double[] prediction, final double[] actual) {
        final double[] errors = new double[prediction.length];
        for (int i = 0; i < prediction.length; i++) {
            errors[i] = Math.pow(prediction[i] - actual[i], 2);
        }
        return Arrays.stream(errors).average().orElse(0);
    }
}
