package com.liemily.recommender.example.cf;

import com.liemily.recommender.example.cf.domain.DataSets;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataSetUtils {
    public DataSets split(final String[][] data, final double testPercentage) {
        final int splitIdx = (int) Math.round(data.length * testPercentage);
        final String[][] testData = Arrays.copyOfRange(data, 0, splitIdx);
        final String[][] trainingData = Arrays.copyOfRange(data, splitIdx, data.length);
        return new DataSets(testData, trainingData);
    }

    public void shuffle(String[][] data) {
        final Random random = ThreadLocalRandom.current();
        for (int i = data.length - 1; i > 0; i--) {
            final int randIdx = random.nextInt(i + 1);
            final String[] randLine = data[randIdx];
            data[randIdx] = data[i];
            data[i] = randLine;
        }
    }
}
