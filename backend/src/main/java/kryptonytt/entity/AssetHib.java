package kryptonytt.entity;

import kryptonytt.domain.Asset;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ASSETS")
public class AssetHib {

    @Id
    @Column(nullable = false, unique=true)
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String identifier;

    @Column
    private BigDecimal amount;

    @ManyToOne
    private PortfolioHib portfolio;

    protected AssetHib() {
    }

    public AssetHib(String identifier, PortfolioHib portfolio, BigDecimal amount) {
        this.identifier = identifier;
        this.portfolio = portfolio;
        this.amount = amount;
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

    public static Asset toAsset(AssetHib assetHib) {
        return new Asset(assetHib.getIdentifier(), assetHib.getAmount());
    }
}
