package com.liemily.recommender.example.math;

import java.util.Arrays;

public abstract class ErrorCalculator {
    public double getError(final double[][] prediction, final double[][] actual) {
        double[] predictionValues = flatten(prediction);
        double[] actualValues = flatten(actual);
        return getError(predictionValues, actualValues);
    }

    double[] flatten(final double[][] matrix) {
        return Arrays.stream(matrix).flatMapToDouble(row -> Arrays.stream(row).filter(i -> i != 0)).toArray();
    }

    abstract double getError(final double[] prediction, final double[] actual);
}
