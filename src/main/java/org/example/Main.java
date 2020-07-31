package org.example;

import org.example.computation.Solver;
import org.example.inputData.Input;
import org.example.inputData.Matrix;
import org.example.outputData.Result;
import org.example.read.MatrixReader;
import org.example.save.ResultSaver;

import java.io.File;
import java.util.Arrays;

public class Main {

    public static final String source = "./src/main/resources/";

    public static void main(String[] args) {

        String inputPath = source + args[1];
        String outputPath = source + args[3];

        File fileToRead = new File(inputPath);
        File fileToSave = new File(outputPath);

        MatrixReader reader = MatrixReader.getInstance();
        ResultSaver saver = ResultSaver.getInstance();

        Input input = reader.readInput(fileToRead);
        Matrix matrix = reader.readMatrix(fileToRead);
        Solver solver = new Solver(input);

        System.out.println("Start solving the equation.");

        Result results = solver.solveMatrix(matrix);
        saver.saveResults(fileToSave, results);

        if (results.getResults() != null) {
            System.out.println("The solution is: " + String.join(",", Arrays.deepToString(results.getResults())));
        } else {
            System.out.println(results.getDescription());
        }

        System.out.println("Saved to file out.txt");
    }
}
