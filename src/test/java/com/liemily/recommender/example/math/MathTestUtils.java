package com.liemily.recommender.example.math;

import static org.junit.Assert.assertEquals;

public class MathTestUtils {
    public void assertMatrixEquals(final double[][] expectedMatrix, final double[][] actualMatrix) {
        assertEquals(expectedMatrix.length, actualMatrix.length);
        assertEquals(expectedMatrix[0].length, actualMatrix[0].length);
        for (int i = 0; i < expectedMatrix.length; i++) {
            for (int j = 0; j < expectedMatrix[0].length; j++) {
                assertEquals("Co-ordinates did not match: i=" + i + ", j=" + j, expectedMatrix[i][j], actualMatrix[i][j], 0.001);
            }
        }
    }
}
