package com.liemily.recommender.example.cf;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;


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
        for (String[] line : data) {
            for (String item : line) {
                assertEquals(expectedItem + "", item);
                expectedItem++;
            }
        }
    }

    @Test
    public void testGetColumn() throws Exception {
        final String[] col = dataReader.getColumn(header, data, "item");
        final String[] expected = new String[]{"1", "4"};
        assertArrayEquals(expected, col);
    }
}
