package com.liemily.recommender.example.cf;

import com.liemily.recommender.example.cf.domain.DataSets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataSetUtils {
    public DataSets split(final String[][] data, final double testPercentage) {
        final int splitIdx = (int) Math.round(data.length * testPercentage);
        final String[][] testData = Arrays.copyOfRange(data, 0, splitIdx);
        final String[][] trainingData = Arrays.copyOfRange(data, splitIdx, data.length);
        return new DataSets(testData, trainingData);
    }

    public void shuffle(final String[][] data) {
        final Random random = ThreadLocalRandom.current();
        for (int i = data.length - 1; i > 0; i--) {
            final int randIdx = random.nextInt(i + 1);
            final String[] randLine = data[randIdx];
            data[randIdx] = data[i];
            data[i] = randLine;
        }
    }

    public String[] getColumn(final String col, final String[] header, final String[][] data) throws NoSuchFieldException {
        if (Arrays.stream(header).noneMatch(col::equals)) {
            throw new NoSuchFieldException(col);
        }
        final int idx = getColIdx(header, col);
        return Arrays.stream(data).map(line -> line[idx]).toArray(String[]::new);
    }

    public int count(final String col, final String[] header, final String[][] data) throws NoSuchFieldException {
        final String[] column = getColumn(col, header, data);
        return (int) Arrays.stream(column).distinct().count();
    }

    public String[][] getEntityToEntityMatrix(final String[] header, final String[][] data, final String rowEntity, final String colEntity, final String valueEntity, String defaultValue) throws NoSuchFieldException {
        final String[] rowEntities = getColumn(rowEntity, header, data);
        final Map<String, Integer> rowIdxs = getNormalisedEntities(rowEntities);

        final String[] colEntities = getColumn(colEntity, header, data);
        final Map<String, Integer> colIdxs = getNormalisedEntities(colEntities);

        final int rowEntityIdx = getColIdx(header, rowEntity);
        final int colEntityIdx = getColIdx(header, colEntity);
        final int valueEntityIdx = getColIdx(header, valueEntity);

        String[][] entityToEntityMatrix = new String[rowIdxs.size()][colIdxs.size()];
        Arrays.stream(entityToEntityMatrix).forEach(row -> Arrays.fill(row, defaultValue));

        for (final String[] row : data) {
            final int rowIdx = rowIdxs.get(row[rowEntityIdx]);
            final int colIdx = colIdxs.get(row[colEntityIdx]);
            entityToEntityMatrix[rowIdx][colIdx] = row[valueEntityIdx];
        }
        return entityToEntityMatrix;
    }

    private Map<String, Integer> getNormalisedEntities(String[] entities) {
        Map<String, Integer> idToIdx = new HashMap<>();
        int count = 0;
        for (final String entity : entities) {
            if (!idToIdx.containsKey(entity)) {
                idToIdx.put(entity, count);
                count++;
            }
        }
        return idToIdx;
    }

    public int getColIdx(final String[] header, final String col) {
        return Arrays.asList(header).indexOf(col);
    }
}
