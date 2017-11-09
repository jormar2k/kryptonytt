package kryptonytt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import kryptonytt.rest.View;

import java.util.Map;

public class KryptonyttUser {

    @JsonIgnore
    private Long id;

    @JsonView(View.Simple.class)
    private String username;

    private String password;

    private String email;

    private Map<String, Object> settings;

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
    public Map<String, Object> getSettings() {
        return settings;
    }
    public void setSettings(Map<String, Object> settings) {
        this.settings = settings;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
