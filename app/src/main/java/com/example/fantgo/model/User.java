package com.example.fantgo.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int id;
    @SerializedName("uid")
    private String uid;
    @SerializedName("email")
    private String email;
    @SerializedName("jwt")
    private String jwt;
    @SerializedName("password")
    private String password;

    public User(int id, String uid, String email, String jwt, String password) {
        this.id = id;
        this.uid = uid;
        this.email = email;
        this.jwt = jwt;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
