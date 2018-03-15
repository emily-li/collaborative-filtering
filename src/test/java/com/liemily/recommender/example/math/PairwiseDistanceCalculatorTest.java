package com.liemily.recommender.example.math;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class PairwiseDistanceCalculatorTest {
    private static PairwiseDistanceCalculator pairwiseDistanceCalculator;
    private static MathTestUtils mathTestUtils;
    private double[][] matrix;

    @BeforeClass
    public static void setupBeforeClass() {
        pairwiseDistanceCalculator = new PairwiseDistanceCalculator();
        mathTestUtils = new MathTestUtils();
    }

    @Before
    public void setup() {
        matrix = new double[][]{
                {2.0, 0.1, 0.1, 0.1},
                {0.1, 5.0, 0.1, 0.1},
                {0.1, 0.1, 8.0, 0.1},
                {0.1, 0.1, 0.1, 11.0}
        };
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
        mathTestUtils.assertMatrixEquals(expectedMatrix, squaredMatrix);
    }

    @Test
    public void testElementWiseMultiplication() {
        final double[][] expectedMatrix = new double[][]{
                {4, 0.01, 0.01, 0.01},
                {0.01, 25, 0.01, 0.01},
                {0.01, 0.01, 64, 0.01},
                {0.01, 0.01, 0.01, 121}
        };
        final double[][] squaredMatrix = pairwiseDistanceCalculator.elementWiseMultiply(matrix, matrix);
        mathTestUtils.assertMatrixEquals(expectedMatrix, squaredMatrix);
    }

    @Test
    public void testCosineSimilarity() {
        final double[][] expectedMatrix = new double[][]{
                {1, 0.07168, 0.06349, 0.05976},
                {0.07168, 1, 0.03297, 0.02943},
                {0.06349, 0.03297, 1, 0.02181},
                {0.05976, 0.02943, 0.02181, 1}
        };
        final double[][] cosineSimilarity = pairwiseDistanceCalculator.cosineSimilarity(matrix);
        mathTestUtils.assertMatrixEquals(expectedMatrix, cosineSimilarity);
    }

    @Test
    public void testCosineDistances() {
        final double[][] expectedMatrix = new double[][]{
                {0, 0.92831, 0.93650, 0.94023},
                {0.92831, 0, 0.96702, 0.97056},
                {0.93650, 0.96702, 0, 0.97818},
                {0.94023, 0.97056, 0.97818, 0}
        };
        final double[][] cosineDistances = pairwiseDistanceCalculator.cosineDistances(matrix);
        mathTestUtils.assertMatrixEquals(expectedMatrix, cosineDistances);
    }

    @Test
    public void testNormalise() {
        final double[][] expectedMatrix = new double[][]{
                {0.99627, 0.04981, 0.04981, 0.04981},
                {0.01998, 0.99940, 0.01998, 0.01998},
                {0.01249, 0.01249, 0.99976, 0.01249},
                {0.00908, 0.00908, 0.00908, 0.99987}
        };
        final double[][] normalised = pairwiseDistanceCalculator.normalise(matrix);
        mathTestUtils.assertMatrixEquals(expectedMatrix, normalised);
    }

    @Test
    public void testNorms() {
        final double[] expectedNorms = new double[]{2.00748599, 5.0029991, 8.00187478, 11.00136355};
        final double[] norms = pairwiseDistanceCalculator.getNorms(matrix);
        assertArrayEquals(expectedNorms, norms, 0.00001);
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
        final double[][] transposedMatrix = pairwiseDistanceCalculator.transpose(matrix);
        mathTestUtils.assertMatrixEquals(expectedMatrix, transposedMatrix);
    }

    @Test
    public void testCorrectSelfDistances() {
        final double[][] expectedMatrix = new double[][]{
                {0, 0.1, 0.1, 0.1},
                {0.1, 0, 0.1, 0.1},
                {0.1, 0.1, 0, 0.1},
                {0.1, 0.1, 0.1, 0}
        };
        final double[][] correctedMatrix = pairwiseDistanceCalculator.correctSelfDistances(matrix);
        mathTestUtils.assertMatrixEquals(expectedMatrix, correctedMatrix);
    }
}
