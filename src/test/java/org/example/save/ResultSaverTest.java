package org.example.save;

import org.example.outputData.Result;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultSaverTest {

    private ResultSaver saver;
    private File file;

    @BeforeEach
    void setUp() throws IOException {
        file = File.createTempFile("filetest", "txt");
        file.deleteOnExit();
        saver = ResultSaver.getInstance();
    }

    @AfterEach
    void tearDown() {
        file.delete();
    }

    @Test
    void saveResults() throws IOException {
        BigDecimal[] results = new BigDecimal[]{new BigDecimal("1.67"), new BigDecimal("2.00"),
                new BigDecimal("3.00")};

        Result result = new Result(results, null);
        saver.saveResults(file, result);

        String expectedString = "1.67\n2.0\n3.0\n";

        assertEquals(expectedString, new String(Files.readAllBytes(file.toPath())));
    }

    @Test
    void saveResultsNoSolution() throws IOException {
        String string = "No solution";

        Result result = new Result(null, string);
        saver.saveResults(file, result);

        String expected = "No solution";
        assertEquals(expected, new String(Files.readAllBytes(file.toPath())));
    }
}