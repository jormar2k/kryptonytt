package kryptonytt.entity;

import kryptonytt.domain.Asset;
import kryptonytt.domain.Portfolio;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
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
    private Date lastModified;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private Collection<AssetHib> assets = new ArrayList<>();

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
    public Collection<AssetHib> getAssets() {
        return assets;
    }
    public void setAssets(Collection<AssetHib> assets) {
        this.assets = assets;
    }
    public Date getLastModified() {
        return lastModified;
    }
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public static Portfolio toPortfolio(PortfolioHib portfolioHib) {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(portfolioHib.getId());
        portfolio.setName(portfolioHib.getName());
        portfolio.setUser(KryptonyttUserHib.toKryptonyttUser(portfolioHib.getUser()));
        portfolio.setPublic(portfolioHib.isPublic());
        portfolio.setLastModified(portfolioHib.getLastModified().toString());

        Collection<Asset> assets = new ArrayList<>();

        for (AssetHib ass : portfolioHib.getAssets()) {
            assets.add(AssetHib.toAsset(ass));
        }

        portfolio.setAssets(assets);

        return portfolio;
    }
}
