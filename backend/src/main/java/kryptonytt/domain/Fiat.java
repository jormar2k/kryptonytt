package kryptonytt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class Fiat {

    private Long id;

    private String identifier;

    private BigDecimal amount;

    private String currency;

    @JsonIgnore
    private Portfolio portfolio;


    public Fiat() {
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
