package org.example;

import java.io.*;
import java.math.BigDecimal;

public class MatrixReader {

    public Matrix readMatrix(File file) {
        Row[] rows;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int numOfEquations = Integer.parseInt(reader.readLine());
            rows = new Row[numOfEquations];

            for (int i = 0; i < numOfEquations; i++) {
                String[] line = reader.readLine().split("\\s+");
                int length = line.length;
                BigDecimal[] coefficients = convertCoefficients(line, length);
                rows[i] = new Row(coefficients);
            }

        } catch (IOException | NumberFormatException e) {
            return null;
        }
        return new Matrix(rows);
    }

    private BigDecimal[] convertCoefficients(String[] line, int length) {
        BigDecimal[] coefficients = new BigDecimal[length];

        for (int j = 0; j < length; j++) {
            coefficients[j] = new BigDecimal(line[j]);
        }
        return coefficients;
    }
}
