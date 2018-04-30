package com.liemily.recommender.example.math;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class MatrixMathUtils {
    public double[] mean(final double[][] matrix) {
        return Arrays.stream(matrix)
                .mapToDouble(row -> Arrays.stream(row)
                        .average()
                        .orElse(0)
                ).toArray();
    }

    public double[][] subtract(final double[][] matrix, final double[] vector) {
        double[][] subtracted = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                subtracted[i][j] = matrix[i][j] - vector[i];
            }
        }
        return subtracted;
    }

    public double[][] add(final double[][] matrix, final double[] vector) {
        double[][] result = new double[matrix.length][vector.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i][j] = matrix[i][j] + vector[i];
            }
        }
        return result;
    }

    public double[][] multiply(final double[][] matrix1, final double[][] matrix2) {
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

    public double[][] elementWiseMultiply(final double[][] matrix1, final double[][] matrix2) {
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

    public double[][] divide(final double[][] dividend, final double[] divisor, boolean transpose) {
        double[][] divided = new double[dividend.length][dividend[0].length];
        for (int i = 0; i < dividend.length; i++) {
            for (int j = 0; j < dividend.length; j++) {
                int divisorIdx = transpose ? i : j;
                divided[i][j] = dividend[i][j] / divisor[divisorIdx];
            }
        }
        return divided;
    }

    public double[][] transpose(final double[][] matrix) {
        double[][] transposed = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }


    /**
     * Normalises matrix by getting its norms and dividing the matrix by the norms
     *
     * @param matrix Matrix of any size
     */
    public double[][] normalise(final double[][] matrix) {
        double[][] normalised = copy(matrix);
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


    double[][] copy(final double[][] matrix) {
        double[][] clone = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                clone[i][j] = matrix[i][j];
            }
        }
        return clone;
    }
}
