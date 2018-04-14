package com.liemily.recommender.example.math.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Emily Li on 14/04/2018.
 */
public class SVDBasedPredictorTest {
    private static SVDBasedPredictor svdBasedPredictor;
    private double[][] matrix;

    @BeforeClass
    public static void setupBeforeClass() {
        svdBasedPredictor = new SVDBasedPredictor();
    }

    @Before
    public void setup() {
        matrix = new double[][]{
                new double[]{0, 1, 2},
                new double[]{0, 1, 2}
        };
    }

    @Test
    public void testMatrixShapesCorrected() {
        final int k = 5;
        final SVDBasedPredictor.SVDs svds = svdBasedPredictor.getSvds(matrix, k);

        final int m = matrix[0].length;
        final int n = matrix.length;
        assertEquals(m, svds.getU()[0].length);
        assertEquals(k, svds.getU().length);
        assertEquals(k, svds.getS().length);
        assertEquals(k, svds.getVt()[0].length);
        assertEquals(n, svds.getVt().length);
    }
}
