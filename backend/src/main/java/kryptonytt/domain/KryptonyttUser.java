package kryptonytt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import kryptonytt.rest.View;

public class KryptonyttUser {

    @JsonIgnore
    private Long id;

    @JsonView(View.Simple.class)
    private String username;

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
