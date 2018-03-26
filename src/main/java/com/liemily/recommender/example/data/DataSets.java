package com.liemily.recommender.example.data;

public class DataSets {
    private final String[][] testSet;
    private final String[][] trainingSet;

    public DataSets(String[][] testSet, String[][] trainingSet) {
        this.testSet = testSet;
        this.trainingSet = trainingSet;
    }

    public String[][] getTestSet() {
        return testSet;
    }

    public String[][] getTrainingSet() {
        return trainingSet;
    }
}
