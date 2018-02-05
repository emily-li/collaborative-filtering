package com.liemily.recommender.example.cf;

import com.liemily.recommender.example.cf.domain.DataSets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataSetUtils {
    public DataSets split(final DataSet data, final double testPercentage) {
        final int splitIdx = (int) Math.round(data.length() * testPercentage);
        final String[][] testData = Arrays.copyOfRange(data.getData(), 0, splitIdx);
        final String[][] trainingData = Arrays.copyOfRange(data.getData(), splitIdx, data.length());
        return new DataSets(testData, trainingData);
    }

    public String[][] getEntityToEntityMatrix(final DataSet data, final String rowEntity, final String colEntity, final String valueEntity, String defaultValue) throws NoSuchFieldException {
        final String[] rowEntities = data.select(rowEntity);
        final Map<String, Integer> rowIdxs = getNormalisedEntities(rowEntities);

        final String[] colEntities = data.select(colEntity);
        final Map<String, Integer> colIdxs = getNormalisedEntities(colEntities);

        final int rowEntityIdx = data.getColIdx(rowEntity);
        final int colEntityIdx = data.getColIdx(colEntity);
        final int valueEntityIdx = data.getColIdx(valueEntity);

        String[][] entityToEntityMatrix = new String[rowIdxs.size()][colIdxs.size()];
        Arrays.stream(entityToEntityMatrix).forEach(row -> Arrays.fill(row, defaultValue));

        for (final String[] row : data.getData()) {
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
}
