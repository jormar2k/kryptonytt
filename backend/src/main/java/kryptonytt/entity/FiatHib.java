package kryptonytt.entity;

import kryptonytt.domain.Fiat;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "FIATS")
public class FiatHib {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private String identifier;

    @Column
    private BigDecimal amount;

    @Column
    private String currency;

    @ManyToOne
    private PortfolioHib portfolio;

    public FiatHib() {
    }

    public FiatHib(Fiat fiat) {
        this.identifier = fiat.getIdentifier();
        this.amount = fiat.getAmount();
        this.currency = fiat.getCurrency();
    }

    public FiatHib(Long id) {
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

    public static Fiat toFiat(FiatHib fiatHib) {
        Fiat fiat = new Fiat();
        fiat.setIdentifier(fiatHib.getIdentifier());
        fiat.setAmount(fiatHib.getAmount());
        fiat.setCurrency(fiatHib.getCurrency());
        fiat.setId(fiatHib.getId());
        return fiat;
    }
}
