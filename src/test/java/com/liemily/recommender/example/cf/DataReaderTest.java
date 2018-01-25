package com.liemily.recommender.example.cf;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.stream.IntStream;

public class DataReaderTest {
    private static DataReader dataReader;

    @BeforeClass
    public static void setupBeforeClass() {
        dataReader = new DataReader();
    }

    @Test
    public void testCanReadHeader() throws Exception {
        String[] header = dataReader.getHeader("testdata/header", "\t");
        assertNotNull(header);
        IntStream.of(0, 3).forEach(i -> assertEquals(String.valueOf(i), header[i]));
    }

    @Test
    public void testCanReadData() throws Exception {
        String[][] data = dataReader.getData("testdata/data", "\t");
        assert data != null;
        int expectedItem = 0;
        for (String[] line : data) {
            for (String item : line) {
                assertEquals(expectedItem + "", item);
                expectedItem++;
            }
        }
    }
}
