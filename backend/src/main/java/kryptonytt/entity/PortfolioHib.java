package kryptonytt.entity;

import kryptonytt.domain.Coin;
import kryptonytt.domain.CustomAsset;
import kryptonytt.domain.Fiat;
import kryptonytt.domain.Portfolio;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "PORTFOLIOS")
@EntityListeners(AuditingEntityListener.class)
public class PortfolioHib implements Serializable {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean isPublic;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private Collection<CoinHib> coins = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private Collection<CustomAssetHib> customAssetHibs = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private Collection<FiatHib> fiatHibs = new ArrayList<>();

    @ManyToOne
    private KryptonyttUserHib user;

    public PortfolioHib(Long id) {
        this.id = id;
    }

    public PortfolioHib() {
    }

    public PortfolioHib(Portfolio portfolio) {
        this.id = portfolio.getId();
        this.name = portfolio.getName();
        this.isPublic = portfolio.isPublic();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public KryptonyttUserHib getUser() {
        return user;
    }
    public void setUser(KryptonyttUserHib user) {
        this.user = user;
    }
    public Boolean isPublic() {
        return isPublic;
    }
    public void setPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }
    public Collection<CoinHib> getCoinHibs() {
        return coins;
    }
    public void setCoinHibs(Collection<CoinHib> assets) {
        this.coins = assets;
    }
    public Date getLastModified() {
        return lastModified;
    }
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Collection<CustomAssetHib> getCustomAssetHibs() {
        return customAssetHibs;
    }
    public void setCustomAssetHibs(Collection<CustomAssetHib> customAssetHibs) {
        this.customAssetHibs = customAssetHibs;
    }
    public Collection<FiatHib> getFiatHibs() {
        return fiatHibs;
    }
    public void setFiatHibs(Collection<FiatHib> fiatHibs) {
        this.fiatHibs = fiatHibs;
    }

    public static Portfolio toPortfolio(PortfolioHib portfolioHib) {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(portfolioHib.getId());
        portfolio.setName(portfolioHib.getName());
//        portfolio.setUser(KryptonyttUserHib.toKryptonyttUser(portfolioHib.getUser()));
        portfolio.setPublic(portfolioHib.isPublic());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String lastModified = sdf.format(portfolioHib.getLastModified());
        portfolio.setLastModified(lastModified);
        String dateCreated = sdf.format(portfolioHib.getLastModified());
        portfolio.setCreated(dateCreated);

        Collection<Coin> coins = new ArrayList<>();
        for (CoinHib ass : portfolioHib.getCoinHibs()) {
            coins.add(CoinHib.toCoin(ass));
        }
        portfolio.setCoins(coins);

        Collection<CustomAsset> customAssets = new ArrayList<>();
        for (CustomAssetHib ass : portfolioHib.getCustomAssetHibs()) {
            customAssets.add(CustomAssetHib.toCustomAsset(ass));
        }
        portfolio.setCustomAssets(customAssets);

        Collection<Fiat> fiats = new ArrayList<>();
        for (FiatHib fiat : portfolioHib.getFiatHibs()) {
            fiats.add(FiatHib.toFiat(fiat));
        }
        portfolio.setFiat(fiats);

        return portfolio;
    }
}
