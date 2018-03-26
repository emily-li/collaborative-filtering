package com.liemily.recommender.example.data;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataSet {
    private final String[] header;
    private final String[][] data;

    DataSet(String[] header, String[][] data) {
        this.header = header;
        this.data = data;
    }

    public String[] select(final String col) throws NoSuchFieldException {
        if (Arrays.stream(header).noneMatch(col::equals)) {
            throw new NoSuchFieldException(col);
        }
        final int idx = getColIdx(col);
        return Arrays.stream(data).map(line -> line[idx]).toArray(String[]::new);
    }

    public String[] distinct(final String col) throws NoSuchFieldException {
        final String[] column = select(col);
        return Arrays.stream(column).distinct().toArray(String[]::new);
    }

    public void shuffle() {
        final Random random = ThreadLocalRandom.current();
        for (int i = data.length - 1; i > 0; i--) {
            final int randIdx = random.nextInt(i + 1);
            final String[] randLine = data[randIdx];
            data[randIdx] = data[i];
            data[i] = randLine;
        }
    }

    public int length() {
        return data.length;
    }

    public String[][] getData() {
        return Arrays.copyOf(data, data.length);
    }

    public int getColIdx(final String col) {
        return Arrays.asList(header).indexOf(col);
    }

    public String[] getHeader() {
        return Arrays.copyOf(header, header.length);
    }
}
