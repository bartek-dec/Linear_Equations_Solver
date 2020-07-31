package org.example.computation;

import org.example.inputData.Input;
import org.example.inputData.Matrix;
import org.example.inputData.Row;
import org.example.outputData.Result;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Solver {

    private Input input;

    public Solver(Input input) {
        this.input = input;
    }

    public Result solveMatrix(Matrix matrix) {
        int size = matrix.getMatrix().length;

        //process lower triangle
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    BigDecimal coeff = matrix.getMatrix()[i][i];

                    if (coeff.compareTo(new BigDecimal("0")) == 0) {

                        int foundRow = findFirstBelow(matrix, i, j);

                        if (foundRow >= 0) {
                            matrix.swapRows(i, foundRow);
                            System.out.println((i + 1) + "R" + " <-> " + "R" + (foundRow + 1));
                            coeff = matrix.getMatrix()[i][i];
                        } else {
                            break;
                        }
                    }

                    if (coeff.compareTo(new BigDecimal("1")) != 0) {
                        toOne(matrix, i, j);
                    }

                    continue;
                }
                toZero(matrix, j, i);
            }
        }

        if (matrix.isContradiction()) {
            return new Result(null, "No solutions");
        }

        int significantEquations = matrix.getNumOfSignificantEquations();
        if (significantEquations < input.getNumOfVariables()) {
            return new Result(null, "Infinitely many solutions");
        }

        //process upper triangle
        int rowLength = matrix.getMatrix()[0].length;
        for (int i = significantEquations - 2; i >= 0; i--) {
            for (int j = rowLength - 2; j > i; j--) {
                toZero(matrix, i, j);
            }
        }

        //get results
        BigDecimal[] results = collectSolutions(matrix, significantEquations);
        return new Result(results, null);
    }

    private BigDecimal[] collectSolutions(Matrix matrix, int size) {
        BigDecimal[] results = new BigDecimal[size];
        int rowLength = matrix.getMatrix()[0].length;

        for (int i = 0; i < size; i++) {
            BigDecimal number = matrix.getMatrix()[i][rowLength - 1].setScale(4, RoundingMode.HALF_DOWN);
            results[i] = number;
        }
        return results;
    }

    private int findFirstBelow(Matrix matrix, int i, int j) {
        for (int k = i + 1; k < matrix.getMatrix().length; k++) {
            if (matrix.getMatrix()[k][j].compareTo(new BigDecimal("0")) != 0) {
                return k;
            }
        }
        return -1;
    }

    private void toZero(Matrix matrix, int i, int j) {
        BigDecimal current = matrix.getMatrix()[i][j].setScale(20, RoundingMode.HALF_DOWN);
        if (current.compareTo(new BigDecimal("0")) != 0) {
            BigDecimal last = matrix.getMatrix()[j][j];
            BigDecimal multiplier = current.divide(last, 20, RoundingMode.HALF_DOWN).multiply(new BigDecimal("-1"));

            Row row = matrix.multiplyRow(j, multiplier);
            matrix.addRow(i, row);

            System.out.println(multiplier.toString() + " * R" + (i + 1) + " + R" + (j + 1) + " -> " + "R" + (j + 1));
        }
    }

    private void toOne(Matrix matrix, int i, int j) {
        BigDecimal divider = matrix.getMatrix()[i][i];
        Row row = matrix.divideRow(i, divider);
        matrix.setRow(j, row);
        System.out.println("R" + (i + 1) + " / " + divider.toString() + " -> " + "R" + (i + 1));
    }
}
