package org.example;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;

public class Main {

    public static final String source = "./src/main/resources/";

    public static void main(String[] args) {

        String inputPath = source + args[1];
        String outputPath = source + args[3];

        File fileToRead = new File(inputPath);
        File fileToSave = new File(outputPath);

        MatrixReader reader = new MatrixReader();
        ResultSaver saver = new ResultSaver();

        Matrix matrix = reader.readMatrix(fileToRead);
        Solver solver = new Solver();

        System.out.println("Start solving the equation.");

        BigDecimal[] results = solver.solveMatrix(matrix);
        saver.saveResults(fileToSave, results);

        System.out.println("The solution is: " + String.join(",", Arrays.deepToString(results)));
        System.out.println("Saved to file out.txt");
    }
}
