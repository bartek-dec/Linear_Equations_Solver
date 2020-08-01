package org.example.inputData;

public class Input {

    private int numOfVariables;
    private int numOfEquations;

    public Input(int numOfVariables, int numOfEquations) {
        this.numOfVariables = numOfVariables;
        this.numOfEquations = numOfEquations;
    }

    public int getNumOfVariables() {
        return numOfVariables;
    }
}
