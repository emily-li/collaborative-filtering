package com.liemily.recommender.example.cf;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        try (Stream<String> dataStream = Files.lines(dataPath)) {
            return dataStream.map(row -> row.split(delim)).toArray(String[][]::new);
        }
    }
}
