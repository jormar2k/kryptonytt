package kryptonytt.JSON;

import java.util.Collection;

public class PortfolioJSON {
    private Collection<AssetJSON> assets;
    private String name;

    public PortfolioJSON() {

    }

    public Collection<AssetJSON> getAssets() {
        return assets;
    }

    public void setAssets(Collection<AssetJSON> assets) {
        this.assets = assets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
