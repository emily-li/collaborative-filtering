package com.liemily.recommender.example.data;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class DataReaderTest {
    private static DataReader dataReader;
    private static DataSet data;

    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        dataReader = new DataReader();
        data = dataReader.getData("testdata/header", "testdata/data", "\t");
    }

    @Test
    public void testCanReadHeader() {
        assertTrue(data.getHeader().length > 0);
        final String[] expected = new String[]{"user", "item", "rating"};
        assertArrayEquals(expected, data.getHeader());
    }

    @Test
    public void testCanReadData() {
        assertTrue(data.length() > 0);
        int expectedItem = 0;
        for (String[] row : data.getData()) {
            for (String item : row) {
                assertEquals(expectedItem + "", item);
                expectedItem++;
            }
        }
    }
}
