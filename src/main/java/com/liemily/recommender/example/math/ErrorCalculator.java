package com.liemily.recommender.example.math;

public abstract class ErrorCalculator {
    public double getError(final double[][] prediction, final double[][] actual) {
        double[] predictionValues = flatten(prediction);
        double[] actualValues = flatten(actual);
        return getError(predictionValues, actualValues);
    }

    private double[] flatten(final double[][] matrix) {
        return new double[]{};
    }

    abstract double getError(final double[] prediction, final double[] actual);
}
