package com.liemily.recommender.example.cf;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DataSetTest {
    private static DataSet data;

    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        DataReader dataReader = new DataReader();
        data = dataReader.getData("testdata/header", "testdata/data2", "\t");
    }

    @Test
    public void testShuffleData() {
        DataSet shuffledData = new DataSet(data.getHeader(), data.getData());
        assertTrue(Arrays.equals(data.getData(), shuffledData.getData()));
        shuffledData.shuffle();
        assertFalse(Arrays.equals(data.getData(), shuffledData.getData()));
    }

    @Test(expected = NoSuchFieldException.class)
    public void testGetInvalidColumn() throws Exception {
        data.select("a");
    }

    @Test
    public void testGetColumn() throws Exception {
        final String[] col = data.select("item");
        final String[] expected = new String[]{"1", "4", "7", "10"};
        assertArrayEquals(expected, col);
    }

    @Test
    public void testGetDistinct() throws Exception {
        final String[] expectedUniqUsers = new String[]{"0", "3", "9"};
        final String[] actualUniqUsers = data.distinct("user");
        assertArrayEquals(expectedUniqUsers, actualUniqUsers);
    }
}
