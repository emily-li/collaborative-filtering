package com.liemily.recommender.example.cf;

import com.liemily.recommender.example.math.MathTestUtils;
import com.liemily.recommender.example.math.MatrixMathUtils;
import org.junit.BeforeClass;
import org.junit.Test;

public class PredictorTest {
    private static Predictor predictor;
    private static MathTestUtils mathTestUtils;

    @BeforeClass
    public static void setupBeforeClass() {
        predictor = new Predictor(new MatrixMathUtils());
        mathTestUtils = new MathTestUtils();
    }

    @Test
    public void testPredict() {
        final double[][] expected = {
                {3.798, 3.16, 2.223, 0.818},
                {-6.895, -5.776, 23.394, -4.403},
                {113.578, 113.35, 145.818, 111.253},
                {-34.066, -32.502, 110.334, -33.766}
        };
        final double[][] ratings = {
                {1, 2, 3, 4},
                {4, 3.32, -2, 1},
                {0, 4, 482, -2},
                {1, 2, 3, 4}
        };
        final double[][] similarity = {
                {0.43, 234, 2.14, -2.1},
                {823.2, 32, 54.2, 1.2},
                {2, 12.3, 1.5, -4.2},
                {1, 2, 3, 4}
        };
        final double[][] predictions = predictor.predict(ratings, similarity, true);
        mathTestUtils.assertMatrixEquals(expected, predictions);
    }
}
