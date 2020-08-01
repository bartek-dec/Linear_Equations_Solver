package org.example.inputData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private Matrix matrix;
    private BigDecimal[][] elements;

    @BeforeEach
    void setUp() {
        elements = new BigDecimal[3][];
        BigDecimal[] row1 = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("1"),
                new BigDecimal("2"), new BigDecimal("9")};

        BigDecimal[] row2 = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("4"),
                new BigDecimal("-3"), new BigDecimal("1")};

        BigDecimal[] row3 = new BigDecimal[]{new BigDecimal("3"), new BigDecimal("6"),
                new BigDecimal("-5"), new BigDecimal("0")};

        elements[0] = row1;
        elements[1] = row2;
        elements[2] = row3;

        matrix = new Matrix(elements);
    }

    @Test
    void getRowExpectedRow() {
        BigDecimal[] expected = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("4"),
                new BigDecimal("-3"), new BigDecimal("1")};

        assertArrayEquals(expected, matrix.getRow(1).getElements());
    }

    @Test
    void getRowExpectedNullWhenNegativeIndex() {
        assertNull(matrix.getRow(-1));
    }

    @Test
    void getRowExpectedNullWhenIndexTooLarge() {
        assertNull(matrix.getRow(4));
    }

    @Test
    void multiplyRow() {
        BigDecimal[] expected = new BigDecimal[]{new BigDecimal("4.00000000000000000000"), new BigDecimal("8.00000000000000000000"),
                new BigDecimal("-6.00000000000000000000"), new BigDecimal("2.00000000000000000000")};

        assertArrayEquals(expected, matrix.multiplyRow(1, new BigDecimal("2")).getElements());
    }

    @Test
    void divideRow() {
        BigDecimal[] expected = new BigDecimal[]{new BigDecimal("0.66666666666666666667"), new BigDecimal("1.33333333333333333333"),
                new BigDecimal("-1.00000000000000000000"), new BigDecimal("0.33333333333333333333")};

        assertArrayEquals(expected, matrix.divideRow(1, new BigDecimal("3")).getElements());
    }

    @Test
    void addRow() {
        BigDecimal[] row = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("1"),
                new BigDecimal("2"), new BigDecimal("9")};

        Row rowToAdd = new Row(4);
        rowToAdd.setElements(row);
        matrix.addRow(1, rowToAdd);

        BigDecimal[] expected = new BigDecimal[]{new BigDecimal("4"), new BigDecimal("5"),
                new BigDecimal("-1"), new BigDecimal("10")};

        assertArrayEquals(expected, matrix.getRow(1).getElements());
    }

    @Test
    void setRowTest() {
        BigDecimal[] row = new BigDecimal[]{new BigDecimal("4"), new BigDecimal("5"),
                new BigDecimal("-1"), new BigDecimal("10")};

        Row rowToSet = new Row(4);
        rowToSet.setElements(row);

        matrix.setRow(0, rowToSet);

        assertArrayEquals(row, matrix.getMatrix()[0]);
    }

    @Test
    void swapRowsTest() {
        matrix.swapRows(0, 2);

        BigDecimal[] row1 = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("1"),
                new BigDecimal("2"), new BigDecimal("9")};

        BigDecimal[] row2 = new BigDecimal[]{new BigDecimal("3"), new BigDecimal("6"),
                new BigDecimal("-5"), new BigDecimal("0")};

        assertArrayEquals(row2, matrix.getRow(0).getElements());
        assertArrayEquals(row1, matrix.getRow(2).getElements());
    }

    @Test
    void isContradictionTestExpectFalse() {
        assertFalse(matrix.isContradiction());
    }

    @Test
    void isContradictionTestExpectTrue() {
        BigDecimal[] row1 = new BigDecimal[]{new BigDecimal("0"), new BigDecimal("0"),
                new BigDecimal("0"), new BigDecimal("9")};

        Row row = new Row(4);
        row.setElements(row1);
        matrix.setRow(2, row);

        assertTrue(matrix.isContradiction());
    }

    @Test
    void getNumOfSignificantEquationsExpected_2() {
        BigDecimal[] row1 = new BigDecimal[]{new BigDecimal("0"), new BigDecimal("0"),
                new BigDecimal("0"), new BigDecimal("0")};

        Row row = new Row(4);
        row.setElements(row1);
        matrix.setRow(2, row);

        assertEquals(2, matrix.getNumOfSignificantEquations());
    }
}