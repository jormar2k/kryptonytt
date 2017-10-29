package kryptonytt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import kryptonytt.rest.View;

import java.math.BigDecimal;

public class Asset {

    @JsonView(View.Simple.class)
    private String identifier;

    @JsonView(View.Simple.class)
    private BigDecimal amount;

    @JsonIgnore
    private Portfolio portfolio;

    public Asset() {
    }

    public Asset(String identifier, BigDecimal amount) {
        this.identifier = identifier;
        this.amount = amount;
    }

    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Portfolio getPortfolio() {
        return portfolio;
    }
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
