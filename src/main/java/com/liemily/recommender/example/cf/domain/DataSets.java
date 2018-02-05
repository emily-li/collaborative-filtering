package com.liemily.recommender.example.cf.domain;

public class DataSets {
    private String[][] testSet;
    private String[][] trainingSet;

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
