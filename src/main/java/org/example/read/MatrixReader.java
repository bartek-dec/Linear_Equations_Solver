package org.example.read;

import org.example.inputData.Input;
import org.example.inputData.Matrix;

import java.io.*;
import java.math.BigDecimal;

public class MatrixReader {

    private MatrixReader() {
    }

    private static class SingletonHelper {
        private static final MatrixReader uniqueInstance = new MatrixReader();
    }

    public static MatrixReader getInstance() {
        return SingletonHelper.uniqueInstance;
    }

    public Matrix readMatrix(File file) {
        BigDecimal[][] matrix;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int numOfEquations = readNumOfEquations(reader);

            matrix = readEquations(reader, numOfEquations);

        } catch (IOException | NumberFormatException e) {
            return null;
        }
        return new Matrix(matrix);
    }

    public Input readInput(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String[] firstLine = reader.readLine().split("\\s+");

            int numOfVariables = Integer.parseInt(firstLine[0]);
            int numOfEquations = Integer.parseInt(firstLine[1]);

            return new Input(numOfVariables, numOfEquations);
        } catch (IOException | NumberFormatException e) {
            return null;
        }
    }

    private int readNumOfEquations(BufferedReader reader) throws IOException, NumberFormatException {
        String[] firstLine = reader.readLine().split("\\s+");

        return Integer.parseInt(firstLine[1]);
    }

    private BigDecimal[][] readEquations(BufferedReader reader, int numOfEquations) throws IOException {
        BigDecimal[][] matrix = new BigDecimal[numOfEquations][];
        for (int i = 0; i < numOfEquations; i++) {
            String[] line = reader.readLine().split("\\s+");
            int length = line.length;
            BigDecimal[] row = convertCoefficients(line, length);
            matrix[i] = row;
        }
        return matrix;
    }

    private BigDecimal[] convertCoefficients(String[] line, int length) {
        BigDecimal[] coefficients = new BigDecimal[length];

        for (int j = 0; j < length; j++) {
            coefficients[j] = new BigDecimal(line[j]);
        }
        return coefficients;
    }
}
