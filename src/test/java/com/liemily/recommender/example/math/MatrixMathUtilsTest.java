package com.liemily.recommender.example.math;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MatrixMathUtilsTest {
    private static MatrixMathUtils matrixMathUtils;
    private static MathTestUtils mathTestUtils;
    private double[][] matrix;
    private double[][] matrix2;
    private double[] vector;

    @BeforeClass
    public static void setupBeforeClass() {
        matrixMathUtils = new MatrixMathUtils();
        mathTestUtils = new MathTestUtils();
    }

    @Before
    public void setup() {
        matrix = new double[][]{
                {1.5, 1.2, -4.2, 111},
                {-8.1, -0.1, -333, -1.3},
                {3.222, 1.93, 878.2, 17.86},
                {1, 2, 3, 4}
        };
        matrix2 = new double[][]{
                {-25, -26, -31, 83},
                {77.5, 85.5, -247, 84},
                {-222, -223, 652, -207},
                {-1.5, -0.5, 0.5, 1.5}
        };
        vector = new double[]{27.375, -85.625, 225.303, 2.5};
    }

    @Test
    public void testMean() {
        final double[] expectedMeans = {27.375, -85.625, 225.303, 2.5};
        final double[] actualMeans = matrixMathUtils.mean(matrix);
        assertArrayEquals(expectedMeans, actualMeans, 0.0001);
    }

    @Test
    public void testAdd() {
        final double[][] expected = {
                {28.875, -84.425, 221.103, 113.5},
                {19.275, -85.725, -107.697, 1.2},
                {30.597, -83.695, 1103.503, 20.36},
                {28.375, -83.625, 228.303, 6.5}
        };
        final double[][] actual = matrixMathUtils.add(matrix, vector);
        mathTestUtils.assertMatrixEquals(expected, actual);
    }

    @Test
    public void testSubtract() {
        final double[][] expected = {
                {-25.875, -26.175, -31.575, 83.625},
                {77.525, 85.525, -247.375, 84.325},
                {-222.081, -223.373, 652.897, -207.443},
                {-1.5, -0.5, 0.5, 1.5}
        };
        final double[][] actual = matrixMathUtils.subtract(matrix, vector);
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
        final double[][] dotProduct = matrixMathUtils.dot(matrix, matrix2);
        mathTestUtils.assertMatrixEquals(expected, dotProduct);
    }

    @Test
    public void testDivideVector() {
        final double[][] expected = {
                {-16.666, -21.667, 7.38, 0.748},
                {-9.568, -855, 0.742, -64.615},
                {-68.901, -115.544, 0.742, -11.59},
                {-1.5, -0.25, 0.167, 0.375}
        };
        final double[][] divided = matrixMathUtils.divide(matrix2, matrix);
        mathTestUtils.assertMatrixEquals(expected, divided);
    }

    @Test
    public void testTranspose() {
        final double[][] matrix = new double[][]{
                {4.03, 0.72, 1.02},
                {0.88, 1.22, 1.12},
                {3.22, 1.32, 64.03}
        };
        final double[][] expectedMatrix = new double[][]{
                {4.03, 0.88, 3.22},
                {0.72, 1.22, 1.32},
                {1.02, 1.12, 64.03}
        };
        final double[][] transposedMatrix = matrixMathUtils.transpose(matrix);
        mathTestUtils.assertMatrixEquals(expectedMatrix, transposedMatrix);
    }
}
