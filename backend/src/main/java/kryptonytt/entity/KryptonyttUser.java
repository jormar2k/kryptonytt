package kryptonytt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class KryptonyttUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    protected KryptonyttUser() {
    }

    public KryptonyttUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
