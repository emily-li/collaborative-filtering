package com.liemily.recommender.example.math;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MatrixMathUtilsTest {
    private static MatrixMathUtils matrixMathUtils;
    private static MathTestUtils mathTestUtils;
    private static double[][] matrix;

    @BeforeClass
    public static void setupBeforeClass() {
        matrixMathUtils = new MatrixMathUtils();
        mathTestUtils = new MathTestUtils();
        matrix = new double[][]{
                {1.5, 1.2, -4.2, 111},
                {-8.1, -0.1, -333, -1.3},
                {3.222, 1.93, 878.2, 17.86},
                {1, 2, 3, 4}
        };
    }

    @Test
    public void testMean() {
        final double[] expectedMeans = {27.375, -85.625, 225.303, 2.5};
        final double[] actualMeans = matrixMathUtils.mean(matrix);
        assertArrayEquals(expectedMeans, actualMeans, 0.0001);
    }

    @Test
    public void testSubtract() {
        final double[][] expected = {
                {-25.875, -26.175, -31.575, 83.625},
                {77.525, 85.525, -247.375, 84.325},
                {-222.081, -223.373, 652.897, -207.443},
                {-1.5, -0.5, 0.5, 1.5}
        };
        final double[] subtractVector = {27.375, -85.625, 225.303, 2.5};
        final double[][] actual = matrixMathUtils.subtract(matrix, subtractVector);
        mathTestUtils.assertMatrixEquals(expected, actual);
    }

    @Test
    public void testDot() {
        final double[][] expected = {
                {821.4, 944.7, -3025.8, 1261.2},
                {74122.7, 74461.7, -216840.85, 68248.35},
                {-194918.165, -195766.287, 572018.738, -181331.064},
                {-542, -526, 1433, -364}
        };
        final double[][] matrix2 = {
                {-25, -26, -31, 83},
                {77.5, 85.5, -247, 84},
                {-222, -223, 652, -207},
                {-1.5, -0.5, 0.5, 1.5}
        };
        final double[][] dotProduct = matrixMathUtils.dot(matrix, matrix2);
        mathTestUtils.assertMatrixEquals(expected, dotProduct);
    }
}
