package com.liemily.recommender.example.cf;

import com.liemily.recommender.example.cf.domain.DataSets;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DataSetUtilsTest {
    private static DataReader dataReader;
    private static DataSetUtils dataSetUtils;
    private static String[] header;
    private static int[][] data;

    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        dataReader = new DataReader();
        dataSetUtils = new DataSetUtils();

        header = dataReader.getHeader("testdata/header", "\t");
        data = dataReader.getData("testdata/data2", "\t");
    }

    @Test
    public void testSplitDataSets() {
        DataSets dataSets = dataSetUtils.split(data, 0.25);
        assertEquals(1, dataSets.getTestSet().length);
        assertEquals(3, dataSets.getTrainingSet().length);
    }

    @Test
    public void testShuffleData() {
        int[][] shuffledData = Arrays.copyOf(data, data.length);
        dataSetUtils.shuffle(shuffledData);
        assertFalse(Arrays.equals(data, shuffledData));
    }

    @Test
    public void testGetColumn() throws Exception {
        final int[] col = dataSetUtils.getColumn("item", header, data);
        final int[] expected = new int[]{1, 4, 7, 10};
        assertArrayEquals(expected, col);
    }

    @Test(expected = NoSuchFieldException.class)
    public void testGetInvalidColumn() throws Exception {
        dataSetUtils.getColumn("a", header, data);
    }

    @Test
    public void testGetUniqueCountForField() throws Exception {
        final long expectedUniqUsers = 3;
        final long actualUniqUsers = dataSetUtils.count("user", header, data);
        assertEquals(expectedUniqUsers, actualUniqUsers);
    }

    @Test
    public void testGetEntityToEntityMap() throws Exception {
        int[][] expectedMatrix = new int[][]{
                new int[]{0, 1, 2},
                new int[]{3, 4, 5},
                new int[]{9, 7, 8},
                new int[]{9, 10, 11}
        };
        int[][] actualMatrix = dataSetUtils.getEntityToEntityMatrix(header, data, "user", "item", "rating");
        assertTrue(Arrays.deepEquals(expectedMatrix, actualMatrix));
    }
}
