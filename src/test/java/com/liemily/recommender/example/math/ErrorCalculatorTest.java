package com.liemily.recommender.example.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ErrorCalculatorTest {
    private static ErrorCalculator errorCalculator;
    private double[][] matrix;

    @BeforeClass
    public static void setupBeforeClass() {
        errorCalculator = new ErrorCalculator() {
            @Override
            double getError(double[] prediction, double[] actual) {
                return 0;
            }
        };
    }

    @Before
    public void setup() {
        matrix = new double[][]{
                {0, 1.5, 2, 3},
                {4, 3, 2, 1},
                {1, 0, -2, 3},
                {0, 0, 0, 0},
                {1, -55.3, 3, 0}
        };
    }

    @Test
    public void testFlatten() {
        final double[] expected = {1.5, 2, 3, 4, 3, 2, 1, 1, -2, 3, 1, -55.3, 3};
        final double[] actual = errorCalculator.flatten(matrix);
        Assert.assertArrayEquals(expected, actual, 0.001);
    }
}
