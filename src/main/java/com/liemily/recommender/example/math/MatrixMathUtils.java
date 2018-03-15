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
}
