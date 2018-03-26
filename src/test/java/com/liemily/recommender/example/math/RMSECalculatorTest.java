package com.liemily.recommender.example.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RMSECalculatorTest {
    private static RMSECalculator rmseCalculator;
    private double[] predictions;
    private double[] actuals;

    @BeforeClass
    public static void setupBeforeClass() {
        rmseCalculator = new RMSECalculator();
    }

    @Before
    public void setup() {
        predictions = new double[]{-4.2, -22.4, -1.2, 4.5};
        actuals = new double[]{0, 0.4, 3, -20};
    }

    @Test
    public void testMSE() {
        final double expected = 288.8425;
        final double mse = rmseCalculator.meanSquaredError(predictions, actuals);
        Assert.assertEquals(expected, mse, 0.0001);
    }

    @Test
    public void testRMSEFlattened() {
        final double expected = 16.9954;
        final double rmse = rmseCalculator.getError(predictions, actuals);
        Assert.assertEquals(expected, rmse, 0.0001);
    }

    @Test
    public void testRMSE() {
        final double expected = 34.9625;
        final double[][] predictions = {
                {1, 2, 3, 4},
                {1.2, 2.2, 3.5, 4.5},
                {52.1, 1, -4.2, 1}
        };
        final double[][] actuals = {
                {1.1, 2.1, 3.1, 4.2},
                {111, 2.1, 3.5, 4.5},
                {1, 2.1, -4.24, 1}
        };
        final double rmse = rmseCalculator.getError(predictions, actuals);
        Assert.assertEquals(expected, rmse, 0.0001);
    }
}
