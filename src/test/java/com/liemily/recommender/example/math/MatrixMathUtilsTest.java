package com.liemily.recommender.example.math;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MatrixMathUtilsTest {
    private static MatrixMathUtils matrixMathUtils;
    private static MathTestUtils mathTestUtils;
    private double[][] matrix;
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
                {28.875, 28.575, 23.175, 138.375},
                {-93.725, -85.725, -418.625, -86.925},
                {228.525, 227.233, 1103.503, 243.163},
                {3.5, 4.5, 5.5, 6.5}
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
        final double[][] matrix2 = {
                {-25, -26, -31, 83},
                {77.5, 85.5, -247, 84},
                {-222, -223, 652, -207},
                {-1.5, -0.5, 0.5, 1.5}
        };
        final double[][] dotProduct = matrixMathUtils.dot(matrix, matrix2);
        mathTestUtils.assertMatrixEquals(expected, dotProduct);
    }

    @Test
    public void testDivideVector() {
        final double[][] expected = {
                {0.055, 0.044, -0.153, 4.055},
                {0.094, 0.002, 3.89, 0.015},
                {0.014, 0.009, 3.898, 0.079},
                {0.4, 0.8, 1.2, 1.6}
        };
        final double[][] divided = matrixMathUtils.divide(matrix, vector);
        mathTestUtils.assertMatrixEquals(expected, divided);
    }

    @Test
    public void testTranspose() {
        final double[][] expectedMatrix = new double[][]{
                {1.5, -8.1, 3.222, 1},
                {1.2, -0.1, 1.93, 2},
                {-4.2, -333, 878.2, 3},
                {111, -1.3, 17.86, 4}
        };
        final double[][] transposedMatrix = matrixMathUtils.transpose(matrix);
        mathTestUtils.assertMatrixEquals(expectedMatrix, transposedMatrix);
    }
}
