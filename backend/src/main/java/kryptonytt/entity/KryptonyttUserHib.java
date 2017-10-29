package kryptonytt.entity;

import kryptonytt.domain.KryptonyttUser;

import javax.persistence.*;

@Entity
@Table(name = "KRYPTONYTT_USERS")
public class KryptonyttUserHib {

    @Id
    @Column(nullable = false, unique=true)
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique=true)
    private String username;

    @Column(nullable = false)
    private String password;

    public KryptonyttUserHib() {
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

    public static KryptonyttUser toKryptonyttUser(KryptonyttUserHib kryptonyttUserHib) {
        KryptonyttUser kryptonyttUser = new KryptonyttUser();
        kryptonyttUser.setId(kryptonyttUserHib.getId());
        kryptonyttUser.setUsername(kryptonyttUserHib.getUsername());
        kryptonyttUser.setPassword(kryptonyttUserHib.getPassword());
        return kryptonyttUser;
    }

}
