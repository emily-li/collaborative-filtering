package com.liemily.recommender.example.cf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.liemily.recommender.example.cf.domain.DataSets;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class DataSetUtilsTest {
    private static DataReader dataReader;
    private static DataSetUtils dataSetUtils;
    private static String[][] data;

    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        dataReader = new DataReader();
        dataSetUtils = new DataSetUtils();

        data = dataReader.getData("testdata/data", "\t");
    }

    @Test
    public void testSplitDataSets() {
        DataSets dataSets = dataSetUtils.split(data, 0.25);
        assertEquals(1, dataSets.getTestSet().length);
        assertEquals(3, dataSets.getTrainingSet().length);
    }

    @Test
    public void testShuffleData() {
        String[][] shuffledData = Arrays.copyOf(data, data.length);
        dataSetUtils.shuffle(shuffledData);
        assertFalse(Arrays.equals(data, shuffledData));
    }
}
