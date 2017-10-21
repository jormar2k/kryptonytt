package kryptonytt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class KryptonyttUser {

    @JsonIgnore
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    public KryptonyttUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
