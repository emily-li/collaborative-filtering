package com.liemily.recommender.example.cf;

import com.liemily.recommender.example.math.MatrixMathUtils;

import java.util.Arrays;

public class Predictor {
    private MatrixMathUtils matrixMathUtils;

    public Predictor(MatrixMathUtils matrixMathUtils) {
        this.matrixMathUtils = matrixMathUtils;
    }

    public double[][] predict(final double[][] data, final double[][] entitySimilarity, boolean normalise) {
        double[][] predictions;
        if (normalise) {
            final double[] means = matrixMathUtils.mean(data);
            final double[][] diff = matrixMathUtils.subtract(data, means);
            final double[][] similarityDiff = matrixMathUtils.dot(entitySimilarity, diff);
            final double[] absSimSum = Arrays.stream(entitySimilarity).mapToDouble(row -> Arrays.stream(row).map(Math::abs).sum()).toArray();
            final double[][] diffOverSim = matrixMathUtils.divide(similarityDiff, absSimSum);
            predictions = matrixMathUtils.add(diffOverSim, means);
        } else {
            predictions = null;
        }
        return predictions;
    }
}
