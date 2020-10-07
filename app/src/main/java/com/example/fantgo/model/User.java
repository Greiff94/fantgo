package com.example.fantgo.model;

public class User {

    private int id;
    private String uid;
    private String email;
    private String jwt;

    public User() {
        this.jwt = jwt;
        this.id = id;
        this.uid = uid;
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public int getId() {
        return id;
    }


    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }
}
