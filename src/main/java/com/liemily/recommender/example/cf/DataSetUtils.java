package com.liemily.recommender.example.cf;

import com.liemily.recommender.example.cf.domain.DataSets;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataSetUtils {
    public DataSets split(final int[][] data, final double testPercentage) {
        final int splitIdx = (int) Math.round(data.length * testPercentage);
        final int[][] testData = Arrays.copyOfRange(data, 0, splitIdx);
        final int[][] trainingData = Arrays.copyOfRange(data, splitIdx, data.length);
        return new DataSets(testData, trainingData);
    }

    public void shuffle(final int[][] data) {
        final Random random = ThreadLocalRandom.current();
        for (int i = data.length - 1; i > 0; i--) {
            final int randIdx = random.nextInt(i + 1);
            final int[] randLine = data[randIdx];
            data[randIdx] = data[i];
            data[i] = randLine;
        }
    }

    public int[] getColumn(final String col, final String[] header, final int[][] data) throws NoSuchFieldException {
        if (Arrays.stream(header).noneMatch(col::equals)) {
            throw new NoSuchFieldException(col);
        }
        final int idx = getColIdx(header, col);
        return Arrays.stream(data).mapToInt(line -> line[idx]).toArray();
    }

    public int count(final String col, final String[] header, final int[][] data) throws NoSuchFieldException {
        final int[] column = getColumn(col, header, data);
        return (int) Arrays.stream(column).distinct().count();
    }

    public int[][] getEntityToEntityMatrix(String[] header, int[][] data, String rowEntity, String colEntity, String valueEntity) throws NoSuchFieldException {
        final int rowCount = count(rowEntity, header, data);
        final int colCount = count(colEntity, header, data);
        final int rowEntityIdx = getColIdx(header, rowEntity);
        final int colEntityIdx = getColIdx(header, colEntity);
        final int valueEntityIdx = getColIdx(header, valueEntity);

        int[][] matrix = new int[rowCount][colCount];
        // TODO: Normalise data so each entity represents an index in the matrix
        for (final int[] row : data) {
            matrix[row[rowEntityIdx] - 1][row[colEntityIdx] - 1] = row[valueEntityIdx]; // Assumes zero-based IDs
        }
        return matrix;
    }

    public int getColIdx(final String[] header, final String col) {
        return Arrays.asList(header).indexOf(col);
    }
}
