package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MatrixReaderTest {

    private final String path = "./src/test/java/resources/in.txt";
    private MatrixReader reader;
    private Matrix expected;
    private File file;

    @BeforeEach
    void setUp() {
        reader = new MatrixReader();

        Row[] rows = new Row[3];
        BigDecimal[] row1 = new BigDecimal[]{new BigDecimal("1"), new BigDecimal("1"),
                new BigDecimal("2"), new BigDecimal("9")};

        BigDecimal[] row2 = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("4"),
                new BigDecimal("-3"), new BigDecimal("1")};

        BigDecimal[] row3 = new BigDecimal[]{new BigDecimal("3"), new BigDecimal("6"),
                new BigDecimal("-5"), new BigDecimal("0")};

        rows[0] = new Row(row1);
        rows[1] = new Row(row2);
        rows[2] = new Row(row3);

        expected = new Matrix(rows);
        file = new File(path);
    }

    @Test
    void readMatrixTest() {
        assertArrayEquals(expected.getRows()[0].getCoefficients(), reader.readMatrix(file).getRows()[0].getCoefficients());
        assertArrayEquals(expected.getRows()[1].getCoefficients(), reader.readMatrix(file).getRows()[1].getCoefficients());
        assertArrayEquals(expected.getRows()[2].getCoefficients(), reader.readMatrix(file).getRows()[2].getCoefficients());
    }

    @Test
    void readMatrixExpectNullWhenFileNotExist() {
        String path = "./src/test/java/resources/in.txtt";
        File nonExistingFile = new File(path);
        assertNull(reader.readMatrix(nonExistingFile));
    }
}