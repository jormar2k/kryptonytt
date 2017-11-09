package kryptonytt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class CustomAsset {

    private Long id;

    private String identifier;

    private BigDecimal amount;

    private BigDecimal price;

    private String currency;

    @JsonIgnore
    private Portfolio portfolio;


    public CustomAsset() {
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
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
