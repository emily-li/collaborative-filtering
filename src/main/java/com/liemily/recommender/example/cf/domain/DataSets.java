package com.liemily.recommender.example.cf.domain;

public class DataSets {
    private int[][] testSet;
    private int[][] trainingSet;

    public DataSets(int[][] testSet, int[][] trainingSet) {
        this.testSet = testSet;
        this.trainingSet = trainingSet;
    }

    public int[][] getTestSet() {
        return testSet;
    }

    public int[][] getTrainingSet() {
        return trainingSet;
    }
}
