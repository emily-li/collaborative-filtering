package com.liemily.recommender.example.math.memory;

import com.liemily.recommender.example.math.MathTestUtils;
import com.liemily.recommender.example.math.MatrixMathUtils;
import com.liemily.recommender.example.math.memory.PairwiseDistanceCalculator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PairwiseDistanceCalculatorTest {
    private static PairwiseDistanceCalculator pairwiseDistanceCalculator;
    private static MathTestUtils mathTestUtils;
    private double[][] matrix;

    @BeforeClass
    public static void setupBeforeClass() {
        pairwiseDistanceCalculator = new PairwiseDistanceCalculator(new MatrixMathUtils());
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
