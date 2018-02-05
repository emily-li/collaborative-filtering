package com.liemily.recommender.example.cf;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class DataReaderTest {
    private static DataReader dataReader;
    private static String[] header;
    private static String[][] data;

    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        dataReader = new DataReader();
        header = dataReader.getHeader("testdata/header", "\t");
        data = dataReader.getData("testdata/data", "\t");
    }

    @Test
    public void testCanReadHeader() {
        assertTrue(header.length > 0);
        final String[] expected = new String[]{"user", "item", "rating"};
        assertArrayEquals(expected, header);
    }

    @Test
    public void testCanReadData() {
        assertTrue(data.length > 0);
        int expectedItem = 0;
        for (String[] row : data) {
            for (String item : row) {
                assertEquals(expectedItem + "", item);
                expectedItem++;
            }
        }
    }
}
