package org.example.inputData;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Matrix {

    private BigDecimal[][] matrix;

    public Matrix(BigDecimal[][] matrix) {
        this.matrix = matrix;
    }

    public BigDecimal[][] getMatrix() {
        return matrix;
    }

    public void setRow(int index, Row row) {
        if (index >= 0 && index < matrix.length) {
            int length = row.getElements().length;
            for (int i = 0; i < length; i++) {
                matrix[index][i] = row.getElements()[i];
            }
        }
    }

    public Row getRow(int index) {
        if (index < 0 || index >= matrix.length) {
            return null;
        }

        int length = matrix[index].length;
        Row row = new Row(length);
        for (int i = 0; i < length; i++) {
            row.getElements()[i] = matrix[index][i];
        }

        return row;
    }

    public Row multiplyRow(int index, BigDecimal multiplier) {
        if (index < 0 || index >= matrix.length) {
            return null;
        }

        int length = matrix[index].length;
        Row row = new Row(length);
        for (int i = 0; i < length; i++) {
            row.getElements()[i] = matrix[index][i].multiply(multiplier, MathContext.DECIMAL32).setScale(20, RoundingMode.HALF_DOWN);
        }
        return row;
    }

    public Row divideRow(int index, BigDecimal divider) {
        if (index < 0 || index >= matrix.length) {
            return null;
        }

        int length = matrix[index].length;
        Row row = new Row(length);
        for (int i = 0; i < length; i++) {
            row.getElements()[i] = matrix[index][i].divide(divider, 20, RoundingMode.HALF_DOWN);
        }
        return row;
    }

    public void addRow(int index, Row rowToAdd) {
        if (index >= 0 && index < matrix.length) {
            Row row = getRow(index);
            int length = row.getElements().length;
            for (int i = 0; i < length; i++) {
                matrix[index][i] = row.getElements()[i].add(rowToAdd.getElements()[i]);
            }
        }
    }

    public void swapRows(int rowToSwap, int foundRow) {
        Row rowTS = getRow(rowToSwap);
        Row row = getRow(foundRow);

        int length = matrix[rowToSwap].length;
        for (int i = 0; i < length; i++) {
            matrix[rowToSwap][i] = row.getElements()[i];
            matrix[foundRow][i] = rowTS.getElements()[i];
        }
    }

    public boolean isContradiction() {
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            Row row = getRow(i);

            boolean areCoefficientsZero = checkCoefficients(row);
            boolean isConstantNotZero = checkConstant(row);

            if (areCoefficientsZero && isConstantNotZero) {
                return true;
            }
        }
        return false;
    }

    public int getNumOfSignificantEquations() {
        int length = matrix.length;
        int counter = 0;
        for (int i = 0; i < length; i++) {
            Row row = getRow(i);

            if (!checkCoefficients(row)) {
                counter++;
            }
        }
        return counter;
    }

    private boolean checkCoefficients(Row row) {
        int rowLength = row.getElements().length;

        for (int j = 0; j < rowLength - 1; j++) {
            if (row.getElements()[j].compareTo(new BigDecimal("0")) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkConstant(Row row) {
        int rowLength = row.getElements().length;

        if (row.getElements()[rowLength - 1].compareTo(new BigDecimal("0")) != 0) {
            return true;
        }
        return false;
    }
}
