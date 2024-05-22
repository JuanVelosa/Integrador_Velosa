package com.example.integradorvelosabackend.Entity;
import jakarta.persistence.*;
@Entity

public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String username;
    private String password;
    private String email;
    private String name;

    public Admin(long id, String username, String password, String email, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public Admin() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
