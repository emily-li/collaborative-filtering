package com.liemily.recommender.example.cf;

import com.liemily.recommender.example.math.MatrixMathUtils;

public class Predictor {
    private MatrixMathUtils matrixMathUtils;

    public Predictor(MatrixMathUtils matrixMathUtils) {
        this.matrixMathUtils = matrixMathUtils;
    }

    public double predict(final double[][] data, final double[][] entitySimilarity, boolean normalise) {
        double prediction;
        if (normalise) {
            final double[] means = matrixMathUtils.mean(data);
            final double[][] diff = matrixMathUtils.subtract(data, means);
            final double[][] similarityAgainstRatings = matrixMathUtils.dot(entitySimilarity, diff);
            final double[][] accRatings = matrixMathUtils.add(similarityAgainstRatings, means);
        } else {

        }
        return 0;
    }
}
