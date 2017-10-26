package kryptonytt.entity;

import kryptonytt.domain.Asset;
import kryptonytt.domain.Portfolio;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "PORTFOLIO")
public class PortfolioHib implements Serializable {

    @Id
    @Column(nullable = false, unique=true)
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isPublic;

    @OneToMany(mappedBy = "portfolio")
    private Collection<AssetHib> assets = new ArrayList<>();

    @ManyToOne
    private KryptonyttUserHib user;

    public PortfolioHib() {
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

    public static Portfolio toPortfolio(PortfolioHib portfolioHib) {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(portfolioHib.getId());
        portfolio.setName(portfolioHib.getName());
        portfolio.setUser(KryptonyttUserHib.toKryptonyttUser(portfolioHib.getUser()));
        portfolio.setPublic(portfolioHib.isPublic());

        Collection<Asset> assets = new ArrayList<>();

        for (AssetHib ass : portfolioHib.getAssets()) {
            assets.add(AssetHib.toAsset(ass));
        }

        portfolio.setAssets(assets);

        return portfolio;
    }
}
