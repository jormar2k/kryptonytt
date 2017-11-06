package kryptonytt.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kryptonytt.domain.KryptonyttUser;
import kryptonytt.domain.Portfolio;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

@Entity
@Table(name = "KRYPTONYTT_USERS")
public class KryptonyttUserHib {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String settings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Collection<PortfolioHib> portfolios;

    public KryptonyttUserHib() {
    }

    public KryptonyttUserHib(KryptonyttUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.settings = new Gson().toJson(user.getSettings());
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSettings() {
        return settings;
    }
    public void setSettings(String settings) {
        this.settings = settings;
    }
    public Collection<PortfolioHib> getPortfolios() {
        return portfolios;
    }
    public void setPortfolios(Collection<PortfolioHib> portfolios) {
        this.portfolios = portfolios;
    }

    public static KryptonyttUser toKryptonyttUser(KryptonyttUserHib kryptonyttUserHib) {
        KryptonyttUser kryptonyttUser = new KryptonyttUser();
        kryptonyttUser.setId(kryptonyttUserHib.getId());
        kryptonyttUser.setUsername(kryptonyttUserHib.getUsername());
        kryptonyttUser.setPassword(kryptonyttUserHib.getPassword());

        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> settingsMap = new Gson().fromJson(kryptonyttUserHib.getSettings(), type);
        for (Map.Entry<String, Object>  entry : settingsMap.entrySet()) {
            Object value = entry.getValue();
            if (value.equals("true")) {
                settingsMap.put(entry.getKey(), true);
            } else if (value.equals("false")) {
                settingsMap.put(entry.getKey(), false);
            }
        }
        kryptonyttUser.setSettings(settingsMap);
        return kryptonyttUser;
    }

}
