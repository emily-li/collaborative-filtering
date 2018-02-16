package com.liemily.recommender.example.metrics;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * Based on pairwise_distances function in https://github.com/scikit-learn/scikit-learn/blob/master/sklearn/metrics/pairwise.py
 * <p>
 * Provides distance defined as 1.0 less similarity
 */
public class PairwiseDistanceCalculator {
    /**
     * @param matrix Square matrix
     * @param metric Metric with which to measure the distance
     * @return Distance matrix, D, with the same dimensions as the input matrix, X,
     * where D[i, j] expresses the distance between the ith and jth vectors of X
     */
    public double[][] calculate(final int[][] matrix, final Metric metric) {
        return new double[0][];
    }

    private double[][] cosineDistance(final int[][] matrix) {
        double[][] distances = getSimilarity(Metric.COSINE);
        Arrays.stream(distances).forEach(line -> Arrays.stream(line).forEach(i -> i = i * -1 + 1));
        correctSelfDistances(distances);
        return distances;
    }

    private double[][] getSimilarity(Metric metric) {
        return new double[0][];
    }

    private double[][] cosineSimilarity() {

        return new double[0][];
    }

    private void correctSelfDistances(double[][] cosineDistances) {
    }

    private void normalise(double[][] matrix) {
        double[] norms = getNorms(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] /= norms[i];
            }
        }
    }

    /**
     * Provides norms of square matrix X as sqrt(sum(X*X))
     *
     * @param matrix Input square matrix
     * @return Row wise norms of the matrix
     */
    private double[] getNorms(double[][] matrix) {
        double[][] squares = square(matrix);
        return Arrays.stream(squares)
                .mapToDouble(vector -> DoubleStream.of(vector).sum())
                .map(Math::sqrt)
                .toArray();
    }

    private double[][] square(final double[][] matrix) {
        double[][] square = new double[matrix.length][matrix[0].length];
        Arrays.stream(square).forEach(row -> Arrays.fill(row, 0));
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[0].length; j++) {
                square[i][j] = matrix[i][j] * matrix[i][j];
            }
        }
        return square;
    }
}
