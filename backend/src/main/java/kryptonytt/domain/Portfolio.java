package kryptonytt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;

public class Portfolio {

    private Long id;

    private String name;

    private Boolean isPublic;

    private String lastModified;

    private String created;

    @JsonIgnore
    private KryptonyttUser user;

    private Collection<Coin> coins;
    private Collection<CustomAsset> customAssets;
    private Collection<Fiat> fiat;

    public Portfolio() {
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
    public KryptonyttUser getUser() {
        return user;
    }
    public void setUser(KryptonyttUser user) {
        this.user = user;
    }
    public Boolean isPublic() {
        return isPublic;
    }
    public void setPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }
    public Collection<Coin> getCoins() {
        return coins;
    }
    public void setCoins(Collection<Coin> coins) {
        this.coins = coins;
    }
    public String getLastModified() {
        return lastModified;
    }
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public Collection<CustomAsset> getCustomAssets() {
        return customAssets;
    }
    public void setCustomAssets(Collection<CustomAsset> customAssets) {
        this.customAssets = customAssets;
    }
    public Collection<Fiat> getFiat() {
        return fiat;
    }
    public void setFiat(Collection<Fiat> fiat) {
        this.fiat = fiat;
    }
}
