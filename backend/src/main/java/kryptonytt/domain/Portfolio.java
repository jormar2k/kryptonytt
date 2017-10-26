package kryptonytt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import kryptonytt.rest.View;

import java.util.Collection;

public class Portfolio {

    @JsonIgnore
    private Long id;

    @JsonView(View.Simple.class)
    private String name;

    @JsonView(View.Simple.class)
    private Boolean isPublic;

    @JsonView(View.Simple.class)
    private KryptonyttUser user;

    @JsonView(View.Simple.class)
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
