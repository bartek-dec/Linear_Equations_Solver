package org.example.save;

import org.example.outputData.Result;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class ResultSaver {

    private ResultSaver() {
    }

    private static class SingletonHelper {
        private static final ResultSaver uniqueInstance = new ResultSaver();
    }

    public static ResultSaver getInstance() {
        return SingletonHelper.uniqueInstance;
    }

    public void saveResults(File file, Result results) {
        try (FileWriter writer = new FileWriter(file)) {
            if (results.getResults() != null) {
                for (BigDecimal result : results.getResults()) {
                    writer.write(Double.toString(result.doubleValue()));
                    writer.write(System.lineSeparator());
                }
            } else {
                writer.write(results.getDescription());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
