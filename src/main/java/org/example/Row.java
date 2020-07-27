package org.example;

import java.math.BigDecimal;

public class Row {

    private BigDecimal[] coefficients;

    public Row(BigDecimal[] coefficients) {
        this.coefficients = coefficients;
    }

    public BigDecimal[] getCoefficients() {
        return coefficients;
    }
}
