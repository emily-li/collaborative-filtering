package com.liemily.recommender.example.cf;

import com.liemily.recommender.example.math.MatrixMathUtils;
import org.junit.Before;
import org.junit.Test;

public class PredictorTest {
    private Predictor predictor;

    @Before
    public void setup() {
        predictor = new Predictor(new MatrixMathUtils());
    }

    @Test
    public void testPredict() {
/*        final double expectedRating;
        final double[][] ratings;
        final double[][] similarity;
        double rating = predictor.predict(ratings, similarity);
        assertEquals(expectedRating, rating, 0.00001);*/
    }
}
