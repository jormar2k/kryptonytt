package kryptonytt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import kryptonytt.rest.View;

import java.math.BigDecimal;

public class Asset {

    private Long id;

    private String identifier;

    private BigDecimal amount;

    @JsonIgnore
    private Portfolio portfolio;

    private Boolean custom;

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
    public void setCustom(Boolean custom) {
        this.custom = custom;
    }
    public Boolean getCustom() {
        return custom;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
