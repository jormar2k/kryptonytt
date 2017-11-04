package kryptonytt.entity;

import kryptonytt.domain.KryptonyttUser;
import kryptonytt.domain.Portfolio;

import javax.persistence.*;
import java.util.Collection;

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
        this.settings = user.getSettings();
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
        kryptonyttUser.setSettings(kryptonyttUserHib.getSettings());
        return kryptonyttUser;
    }

}
