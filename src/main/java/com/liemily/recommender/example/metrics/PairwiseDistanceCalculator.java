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
        normedMatrix = normalise(normedMatrix);
        double[][] transposedMatrix = transpose(normedMatrix);
        return multiply(normedMatrix, transposedMatrix);
    }

    double[][] transpose(final double[][] matrix) {
        double[][] transposed = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
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

    /**
     * Normalises matrix by getting its norms and dividing the matrix by the norms
     *
     * @param matrix Matrix of any size
     */
    double[][] normalise(final double[][] matrix) {
        double[][] normalised = matrix.clone();
        double[] norms = getNorms(normalised);
        for (int i = 0; i < normalised.length; i++) {
            for (int j = 0; j < normalised[0].length; j++) {
                normalised[i][j] /= norms[i];
            }
        }
        return normalised;
    }

    /**
     * Provides norms of square matrix X as sqrt(sum(X*X))
     *
     * @param matrix Input square matrix
     * @return Row wise norms of the matrix
     */
    double[] getNorms(final double[][] matrix) {
        double[][] squares = elementWiseMultiply(matrix, matrix);
        return Arrays.stream(squares)
                .mapToDouble(vector -> DoubleStream.of(vector).sum())
                .map(Math::sqrt)
                .toArray();
    }

    double[][] multiply(final double[][] matrix1, final double[][] matrix2) {
        double[][] multiplied = new double[matrix1.length][matrix2[0].length];
        Arrays.stream(multiplied).forEach(row -> Arrays.fill(row, 0.0));

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    multiplied[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return multiplied;
    }

    double[][] elementWiseMultiply(final double[][] matrix1, final double[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            throw new RuntimeException("Matrices must match in dimension for element wise multiplication");
        }
        double[][] multiplied = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                multiplied[i][j] = matrix1[i][j] * matrix2[i][j];
            }
        }
        return multiplied;
    }

}
