package com.liemily.recommender.example.cf;

import com.liemily.recommender.example.cf.domain.DataSets;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataSetUtilsTest {
    private static DataReader dataReader;
    private static DataSetUtils dataSetUtils;
    private static DataSet data;

    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        dataReader = new DataReader();
        dataSetUtils = new DataSetUtils();

        data = dataReader.getData("testdata/header", "testdata/data2", "\t");
    }

    @Test
    public void testSplitDataSets() {
        DataSets dataSets = dataSetUtils.split(data, 0.25);
        assertEquals(1, dataSets.getTestSet().length);
        assertEquals(3, dataSets.getTrainingSet().length);
    }

    @Test
    public void testGetEntityToEntityMap() throws Exception {
        final String defaultValue = "";
        final String[][] expectedMatrix = new String[][]{
                new String[]{"2", defaultValue, defaultValue, defaultValue},
                new String[]{defaultValue, "5", defaultValue, defaultValue},
                new String[]{defaultValue, defaultValue, "8", "11"}
        };
        final String[][] actualMatrix = dataSetUtils.getEntityToEntityMatrix(data, "user", "item", "rating", defaultValue);
        assertTrue(Arrays.deepEquals(expectedMatrix, actualMatrix));
    }
}
