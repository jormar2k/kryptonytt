package kryptonytt.entity;

import kryptonytt.domain.Coin;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "COINS")
public class CoinHib {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private String identifier;

    @Column
    private BigDecimal amount;

    @ManyToOne
    private PortfolioHib portfolio;

    public CoinHib() {
    }

    public CoinHib(Coin coin) {
        this.identifier = coin.getIdentifier();
        this.amount = coin.getAmount();
    }

    public CoinHib(Long id) {
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

    public static Coin toCoin(CoinHib coinHib) {
        Coin coin = new Coin(coinHib.getIdentifier(), coinHib.getAmount());
        coin.setId(coinHib.getId());
        return coin;
    }
}
