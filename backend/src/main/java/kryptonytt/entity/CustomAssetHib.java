package kryptonytt.entity;

import kryptonytt.domain.CustomAsset;
import kryptonytt.domain.Fiat;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CUSTOM_ASSETS")
public class CustomAssetHib {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private String identifier;

    @Column
    private BigDecimal amount;

    @Column
    private BigDecimal price;

    @Column
    private String currency;

    @ManyToOne
    private PortfolioHib portfolio;

    public CustomAssetHib() {
    }

    public CustomAssetHib(CustomAsset ass) {
        this.identifier = ass.getIdentifier();
        this.amount = ass.getAmount();
        this.currency = ass.getCurrency();
        this.price = ass.getPrice();
    }

    public CustomAssetHib(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public PortfolioHib getPortfolio() {
        return portfolio;
    }
    public void setPortfolio(PortfolioHib portfolio) {
        this.portfolio = portfolio;
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

    public static CustomAsset toCustomAsset(CustomAssetHib customAssetHib) {
        CustomAsset customAsset = new CustomAsset();
        customAsset.setIdentifier(customAssetHib.getIdentifier());
        customAsset.setAmount(customAssetHib.getAmount());
        customAsset.setCurrency(customAssetHib.getCurrency());
        customAsset.setPrice(customAssetHib.getPrice());
        customAsset.setId(customAssetHib.getId());
        return customAsset;
    }
}
