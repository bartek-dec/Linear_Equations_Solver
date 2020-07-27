package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Solver {

    public BigDecimal[] solveMatrix(Matrix matrix) {
        int size = matrix.getRows().length;

        BigDecimal[] result = new BigDecimal[size];
        BigDecimal current;

        //process lower triangle
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    BigDecimal coeff = matrix.getRows()[i].getCoefficients()[i];
                    if (!Objects.equals(coeff, new BigDecimal("1"))) {
                        BigDecimal divider = matrix.getRows()[i].getCoefficients()[i];
                        Row row = matrix.divideRow(i, divider);
                        matrix.setRow(j, row);

                        System.out.println("R" + (i + 1) + "/" + divider.toString() + " -> " + "R" + (i + 1));
                    }
                    continue;
                }

                current = matrix.getRows()[j].getCoefficients()[i].setScale(20,RoundingMode.HALF_DOWN);
                if (!Objects.equals(current, new BigDecimal("0"))) {
                    BigDecimal first = matrix.getRows()[i].getCoefficients()[i];
                    BigDecimal multiplier = current.divide(first, 20, RoundingMode.HALF_DOWN).multiply(new BigDecimal("-1"));

                    Row row = matrix.multiplyRow(i, multiplier);
                    matrix.addRow(j, row);

                    System.out.println(multiplier.toString() + "R" + (i + 1) + " + R" + (j + 1) + " -> " + "R" + (j + 1));
                }
            }
        }

        //process upper triangle
        for (int i = size - 2; i >= 0; i--) {
            for (int j = size - 1; j > i; j--) {
                current = matrix.getRows()[i].getCoefficients()[j].setScale(20,RoundingMode.HALF_DOWN);
                if (!Objects.equals(current, new BigDecimal("0"))) {
                    BigDecimal last = matrix.getRows()[j].getCoefficients()[j];
                    BigDecimal multiplier = current.divide(last, 20, RoundingMode.HALF_DOWN).multiply(new BigDecimal("-1"));

                    Row row = matrix.multiplyRow(j, multiplier);
                    matrix.addRow(i, row);

                    System.out.println(multiplier.toString() + "R" + (i + 1) + " + R" + (j + 1) + " -> " + "R" + (j + 1));
                }
            }
        }

        //get results
        for (int i = 0; i < size; i++) {
            BigDecimal number = matrix.getRows()[i].getCoefficients()[size].setScale(4, RoundingMode.HALF_DOWN);
            result[i] = number;
        }

        return result;
    }
}
