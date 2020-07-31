package org.example.read;

import org.example.inputData.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MatrixReaderTest {

    private final String path = "./src/test/java/resources/in.txt";
    private MatrixReader reader;
    private Matrix expected;
    private File file;
    private BigDecimal[][] elements;

    @BeforeEach
    void setUp() {
        reader = MatrixReader.getInstance();
        elements = new BigDecimal[3][];

        BigDecimal[] row1 = new BigDecimal[]{new BigDecimal("1"), new BigDecimal("1"),
                new BigDecimal("2"), new BigDecimal("9")};

        BigDecimal[] row2 = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("4"),
                new BigDecimal("-3"), new BigDecimal("1")};

        BigDecimal[] row3 = new BigDecimal[]{new BigDecimal("3"), new BigDecimal("6"),
                new BigDecimal("-5"), new BigDecimal("0")};

        elements[0] = row1;
        elements[1] = row2;
        elements[2] = row3;

        expected = new Matrix(elements);
        file = new File(path);
    }

    @Test
    void readMatrixTest() {
        assertArrayEquals(expected.getMatrix()[0], reader.readMatrix(file).getMatrix()[0]);
        assertArrayEquals(expected.getMatrix()[1], reader.readMatrix(file).getMatrix()[1]);
        assertArrayEquals(expected.getMatrix()[2], reader.readMatrix(file).getMatrix()[2]);
    }

    @Test
    void readMatrixExpectNullWhenFileNotExist() {
        String path = "./src/test/java/resources/in.txtt";
        File nonExistingFile = new File(path);
        assertNull(reader.readMatrix(nonExistingFile));
    }
}