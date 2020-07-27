package org.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Matrix {

    private Row[] rows;

    public Matrix(Row[] rows) {
        this.rows = rows;
    }

    public Row[] getRows() {
        return rows;
    }

    public void setRow(int index, Row row) {
        if (index >= 0 && index < rows.length) {
            rows[index] = row;
        }
    }

    public Row getRow(int index) {
        if (index < 0 || index >= rows.length) {
            return null;
        }
        return rows[index];
    }

    public Row multiplyRow(int index, BigDecimal multiplier) {
        if (index < 0 || index >= rows.length) {
            return null;
        }

        int length = rows[index].getCoefficients().length;
        BigDecimal[] newRow = new BigDecimal[length];
        for (int i = 0; i < length; i++) {
            newRow[i] = rows[index].getCoefficients()[i].multiply(multiplier, MathContext.DECIMAL32);
        }
        return new Row(newRow);
    }

    public Row divideRow(int index, BigDecimal divider) {
        if (index < 0 || index >= rows.length) {
            return null;
        }

        int length = rows[index].getCoefficients().length;
        BigDecimal[] newRow = new BigDecimal[length];
        for (int i = 0; i < length; i++) {
            newRow[i] = rows[index].getCoefficients()[i].divide(divider, 20, RoundingMode.HALF_DOWN);
        }
        return new Row(newRow);
    }

    public Row addRow(int index, Row rowToAdd) {
        if (index < 0 || index >= rows.length) {
            return null;
        }

        Row row = rows[index];
        int length = row.getCoefficients().length;
        for (int i = 0; i < length; i++) {
            row.getCoefficients()[i] = row.getCoefficients()[i].add(rowToAdd.getCoefficients()[i]);
        }
        return row;
    }
}
