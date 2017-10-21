package kryptonytt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class Portfolio {

    @JsonIgnore
    private Long id;

    private String name;

    private Boolean isPublic;

    private KryptonyttUser user;

    private Collection<Asset> assets;

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
    public Collection<Asset> getAssets() {
        return assets;
    }
    public void setAssets(Collection<Asset> assets) {
        this.assets = assets;
    }
}
