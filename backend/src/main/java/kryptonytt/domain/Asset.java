package kryptonytt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import kryptonytt.rest.View;

import java.math.BigDecimal;

public class Asset {

    @JsonView(View.Simple.class)
    private String name;

    @JsonView(View.Simple.class)
    private BigDecimal amount;

    @JsonIgnore
    private Portfolio portfolio;

    public Asset() {
    }

    public Asset(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
