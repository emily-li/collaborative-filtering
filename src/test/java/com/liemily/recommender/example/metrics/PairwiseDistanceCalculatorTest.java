package com.liemily.recommender.example.metrics;

import com.liemily.recommender.example.cf.DataReader;
import com.liemily.recommender.example.cf.DataSet;
import com.liemily.recommender.example.cf.DataSetUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PairwiseDistanceCalculatorTest {
    private static PairwiseDistanceCalculator pairwiseDistanceCalculator;
    private static double[][] matrix;


    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        pairwiseDistanceCalculator = new PairwiseDistanceCalculator();
        DataReader dataReader = new DataReader();
        DataSetUtils dataSetUtils = new DataSetUtils();

        DataSet data = dataReader.getData("testdata/header", "testdata/data", "\t");
        String[][] ratingsMatrix = dataSetUtils.getEntityToEntityMatrix(data, "user", "item", "rating", "0.1");
        matrix = Arrays.stream(ratingsMatrix).map(row -> Arrays.stream(row).mapToDouble(Double::parseDouble).toArray()).toArray(double[][]::new);
    }

    @Test
    public void testMultiplication() {
        final double[][] expectedMatrix = new double[][]{
                {4.03, 0.72, 1.02, 1.32},
                {0.72, 25.03, 1.32, 1.62},
                {1.02, 1.32, 64.03, 1.92},
                {1.32, 1.62, 1.92, 121.03}
        };
        final double[][] squaredMatrix = pairwiseDistanceCalculator.multiply(matrix, matrix);
        assertMatrixEquals(expectedMatrix, squaredMatrix);
    }

    @Test
    public void testTranspose() {
        matrix = new double[][]{
                {4.03, 0.72, 1.02},
                {0.88, 1.22, 1.12},
                {3.22, 1.32, 64.03}
        };
        final double[][] expectedMatrix = new double[][]{
                {4.03, 0.88, 3.22},
                {0.72, 1.22, 1.32},
                {1.02, 1.12, 64.03}
        };
        final double[][] transposedMatrix = pairwiseDistanceCalculator.transpose(matrix);
        assertMatrixEquals(expectedMatrix, transposedMatrix);
    }

    @Test
    public void testCorrectSelfDistances() {
        matrix = new double[][]{
                {0, 0.01, -0.01},
                {-1, 2, 2.1},
                {1.99, 1.999999, 64.03}
        };
        final double[][] expectedMatrix = new double[][]{
                {0, 0.01, 0},
                {0, 2, 2},
                {1.99, 1.999999, 2}
        };
        final double[][] correctedMatrix = pairwiseDistanceCalculator.correctSelfDistances(matrix);
        assertMatrixEquals(expectedMatrix, correctedMatrix);
    }

    private void assertMatrixEquals(final double[][] expectedMatrix, final double[][] actualMatrix) {
        assertEquals(expectedMatrix.length, actualMatrix.length);
        assertEquals(expectedMatrix[0].length, actualMatrix[0].length);
        for (int i = 0; i < expectedMatrix.length; i++) {
            for (int j = 0; j < expectedMatrix[0].length; j++) {
                assertEquals(expectedMatrix[i][j], actualMatrix[i][j], 0.00001);
            }
        }
    }
}
