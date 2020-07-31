package org.example.outputData;

import java.math.BigDecimal;

public class Result {

    private BigDecimal[] results;
    private String description;

    public Result(BigDecimal[] results, String description) {
        this.results = results;
        this.description = description;
    }

    public BigDecimal[] getResults() {
        return results;
    }

    public String getDescription() {
        return description;
    }
}
