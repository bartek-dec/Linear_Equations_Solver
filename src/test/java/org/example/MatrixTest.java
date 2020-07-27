package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private Matrix matrix;
    private Row[] rows;

    @BeforeEach
    void setUp() {
        rows = new Row[3];
        BigDecimal[] row1 = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("1"),
                new BigDecimal("2"), new BigDecimal("9")};

        BigDecimal[] row2 = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("4"),
                new BigDecimal("-3"), new BigDecimal("1")};

        BigDecimal[] row3 = new BigDecimal[]{new BigDecimal("3"), new BigDecimal("6"),
                new BigDecimal("-5"), new BigDecimal("0")};

        rows[0] = new Row(row1);
        rows[1] = new Row(row2);
        rows[2] = new Row(row3);

        matrix = new Matrix(rows);
    }

    @Test
    void getRowExpectedRow() {
        BigDecimal[] expected = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("4"),
                new BigDecimal("-3"), new BigDecimal("1")};

        assertArrayEquals(expected, matrix.getRow(1).getCoefficients());
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
        BigDecimal[] expected = new BigDecimal[]{new BigDecimal("4"), new BigDecimal("8"),
                new BigDecimal("-6"), new BigDecimal("2")};

        assertArrayEquals(expected, matrix.multiplyRow(1, new BigDecimal("2")).getCoefficients());
    }

    @Test
    void divideRow() {
        BigDecimal[] expected = new BigDecimal[]{new BigDecimal("0.66666666666666666667"), new BigDecimal("1.33333333333333333333"),
                new BigDecimal("-1.00000000000000000000"), new BigDecimal("0.33333333333333333333")};

        assertArrayEquals(expected, matrix.divideRow(1, new BigDecimal("3")).getCoefficients());
    }

    @Test
    void addRow() {
        BigDecimal[] row = new BigDecimal[]{new BigDecimal("2"), new BigDecimal("1"),
                new BigDecimal("2"), new BigDecimal("9")};
        Row rowToAdd = new Row(row);

        BigDecimal[] expected = new BigDecimal[]{new BigDecimal("4"), new BigDecimal("5"),
                new BigDecimal("-1"), new BigDecimal("10")};

        assertArrayEquals(expected, matrix.addRow(1, rowToAdd).getCoefficients());
    }

    @Test
    void setRowTest() {
        BigDecimal[] row = new BigDecimal[]{new BigDecimal("4"), new BigDecimal("5"),
                new BigDecimal("-1"), new BigDecimal("10")};

        Row rowToSet = new Row(row);
        matrix.setRow(0, rowToSet);

        assertArrayEquals(row, matrix.getRows()[0].getCoefficients());
    }
}