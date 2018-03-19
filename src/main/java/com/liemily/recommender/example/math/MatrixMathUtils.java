package com.liemily.recommender.example.math;

import java.util.Arrays;

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

    public double[][] dot(final double[][] matrix1, final double[][] matrix2) {
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
}
