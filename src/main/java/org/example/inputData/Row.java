package org.example.inputData;

import java.math.BigDecimal;

public class Row {

    private BigDecimal[] elements;

    public Row(int size) {
        this.elements = new BigDecimal[size];
    }

    public BigDecimal[] getElements() {
        return elements;
    }

    public void setElements(BigDecimal[] elements) {
        this.elements = elements;
    }
}
