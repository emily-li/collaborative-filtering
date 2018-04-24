package com.liemily.recommender.example.math.memory;

import com.liemily.recommender.example.math.MatrixMathUtils;
import com.liemily.recommender.example.math.Metric;

/**
 * Based on pairwise_distances function in https://github.com/scikit-learn/scikit-learn/blob/master/sklearn/metrics/pairwise.py
 * <p>
 * Provides distance defined as 1.0 less similarity
 */
public class PairwiseDistanceCalculator {
    private MatrixMathUtils matrixMathUtils;

    public PairwiseDistanceCalculator(MatrixMathUtils matrixMathUtils) {
        this.matrixMathUtils = matrixMathUtils;
    }

    /**
     * @param matrix Square matrix
     * @param metric Metric with which to measure the distance
     * @return Distance matrix, D, with the same dimensions as the input matrix, X,
     * where D[i, j] expresses the distance between the ith and jth vectors of X
     */
    public double[][] calculate(final double[][] matrix, final Metric metric) {
        switch (metric) {
            case COSINE:
                return cosineDistances(matrix);
            default:
                throw new UnsupportedOperationException("Only metric type Cosine currently supported");
        }
    }

    double[][] cosineDistances(final double[][] matrix) {
        double[][] distances = cosineSimilarity(matrix);
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[0].length; j++) {
                distances[i][j] *= -1;
                distances[i][j] += 1;
            }
        }
        distances = correctSelfDistances(distances);
        return distances;
    }

    double[][] cosineSimilarity(final double[][] matrix) {
        double[][] normedMatrix = matrix.clone();
        normedMatrix = matrixMathUtils.normalise(normedMatrix);
        double[][] transposedMatrix = matrixMathUtils.transpose(normedMatrix);
        return matrixMathUtils.multiply(normedMatrix, transposedMatrix);
    }

    /**
     * Corrects matrix to set distances between vectors and themselves as 0
     *
     * @param distances Distances to correct. Matrix of any size
     * @return Corrected matrix
     */
    double[][] correctSelfDistances(final double[][] distances) {
        final double[][] correctedDistances = new double[distances.length][distances[0].length];

        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[0].length; j++) {
                correctedDistances[i][j] = i == j ? 0 : distances[i][j];
            }
        }
        return correctedDistances;
    }
}
