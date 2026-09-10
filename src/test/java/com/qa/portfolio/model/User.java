package com.qa.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // <-- EZ A KULCS! A többi plusz mezőt figyelmen kívül hagyja
public class User {
    private int id;
    private String name;
    private String email;

    // Üres konstruktor (A Jackson könyvtárnak KELL a JSON válaszok beolvasásához!)
    public User() {}

    // Paraméteres konstruktor (Új adatok létrehozásához a tesztekben)
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getterek és Setterek
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}