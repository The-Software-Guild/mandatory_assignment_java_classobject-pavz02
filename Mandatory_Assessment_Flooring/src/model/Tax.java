package model;

import java.math.BigDecimal;

public class Tax {
    String StateAbbreviation;
    String StateName;
    BigDecimal TaxRate;

    public Tax(String stateAbbreviation, String stateName, BigDecimal taxRate) {
        StateAbbreviation = stateAbbreviation;
        StateName = stateName;
        TaxRate = taxRate;
    }

    public String getStateAbbreviation() {
        return StateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        StateAbbreviation = stateAbbreviation;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        TaxRate = taxRate;
    }
}
