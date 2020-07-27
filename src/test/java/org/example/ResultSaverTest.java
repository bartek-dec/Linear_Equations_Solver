package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class ResultSaverTest {

    private ResultSaver saver;
    private File file;

    @BeforeEach
    void setUp() throws IOException {
        file = File.createTempFile("filetest", "txt");
        file.deleteOnExit();
        saver = new ResultSaver();
    }

    @AfterEach
    void tearDown() {
        file.delete();
    }

    @Test
    void saveResults() throws IOException{
        BigDecimal[] results = new BigDecimal[]{new BigDecimal("1.67"), new BigDecimal("2.00"),
                new BigDecimal("3.00")};

        saver.saveResults(file, results);

        String expectedString = "1.67\n2.0\n3.0\n";

        assertEquals(expectedString, new String(Files.readAllBytes(file.toPath())));
    }
}