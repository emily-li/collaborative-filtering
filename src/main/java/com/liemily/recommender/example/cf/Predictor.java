package com.liemily.recommender.example.cf;

import com.liemily.recommender.example.math.MatrixMathUtils;

import java.util.Arrays;

public class Predictor {
    private MatrixMathUtils matrixMathUtils;

    public Predictor(MatrixMathUtils matrixMathUtils) {
        this.matrixMathUtils = matrixMathUtils;
    }

    public double[][] predict(final double[][] data, final double[][] similarity, boolean normalise) {
        double[][] predictions;
        double[][] similarityDiff;

        double[] means = null;
        if (normalise) {
            means = matrixMathUtils.mean(data);
            final double[][] diff = matrixMathUtils.subtract(data, means);
            similarityDiff = matrixMathUtils.dot(similarity, diff);
        } else {
            similarityDiff = matrixMathUtils.dot(data, similarity);
        }

        final double[] absSimSum = Arrays.stream(similarity).mapToDouble(row -> Arrays.stream(row).map(Math::abs).sum()).toArray();
        final double[][] diffOverSim = normalise ? matrixMathUtils.divide(similarityDiff, absSimSum, true) : matrixMathUtils.divide(similarityDiff, absSimSum, false);

        predictions = normalise && means != null ? matrixMathUtils.add(diffOverSim, means) : diffOverSim;
        return predictions;
    }
}
