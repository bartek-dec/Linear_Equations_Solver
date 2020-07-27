package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class ResultSaver {

    public void saveResults(File file, BigDecimal[] results) {

        try (FileWriter writer = new FileWriter(file)) {
            for (BigDecimal result : results) {
                writer.write(Double.toString(result.doubleValue()));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
