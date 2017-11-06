package kryptonytt.entity;

import kryptonytt.domain.Asset;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ASSETS")
public class AssetHib {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private String identifier;

    @Column
    private BigDecimal amount;

    @Column
    private Boolean custom;

    @ManyToOne
    private PortfolioHib portfolio;

    public AssetHib() {
    }

    public AssetHib(Asset ass) {
        this.identifier = ass.getIdentifier();
        this.amount = ass.getAmount();
        this.custom = ass.getCustom();
    }

    public AssetHib(Long id) {
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
    public Boolean getCustom() {
        return custom;
    }
    public void setCustom(Boolean custom) {
        this.custom = custom;
    }

    public static Asset toAsset(AssetHib assetHib) {
        Asset asset = new Asset(assetHib.getIdentifier(), assetHib.getAmount());
        asset.setCustom(assetHib.getCustom());
        asset.setId(assetHib.getId());
        return asset;
    }
}
