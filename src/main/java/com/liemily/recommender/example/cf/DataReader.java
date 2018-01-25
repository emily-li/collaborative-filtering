package com.liemily.recommender.example.cf;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class DataReader {
    public String[] getHeader(final String headerFilePath, final String delim) throws URISyntaxException, IOException {
        final Path headerPath = Paths.get(ClassLoader.getSystemResource(headerFilePath).toURI());
        try (Stream<String> headerStream = Files.lines(headerPath)) {
            final String header = headerStream.iterator().next();
            return header.split(delim);
        }
    }

    public String[][] getData(final String dataFilePath, final String delim) throws URISyntaxException, IOException {
        final Path dataPath = Paths.get(ClassLoader.getSystemResource(dataFilePath).toURI());
        String[] data;
        try (Stream<String> headerStream = Files.lines(dataPath)) {
            data = headerStream.toArray(String[]::new);
        }
        return Arrays.stream(data).map(line -> line.split(delim)).toArray(String[][]::new);
    }

    public String[] getColumn(final String[] header, final String[][] data, final String col) {
        final int idx = Arrays.asList(header).indexOf(col);
        return Arrays.stream(data).map(line -> line[idx]).toArray(String[]::new);
    }
}
