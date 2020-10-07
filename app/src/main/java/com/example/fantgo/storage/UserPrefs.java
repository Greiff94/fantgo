package com.example.fantgo.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class UserPrefs {
    private static final String USER_PREFS = "USER_PREFS";
    private SharedPreferences userSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    public UserPrefs(Context context){
        this.userSharedPrefs = context.getSharedPreferences(USER_PREFS,
                                                            Activity.MODE_PRIVATE);
        this.prefsEditor = userSharedPrefs.edit();
    }

    //---------------------------GETTERS----------------------------//
    public String getEmail() {
        return userSharedPrefs.getString(Config.KEY_EMAIL, "");
    }
    public String getUserName(){
        return userSharedPrefs.getString(Config.KEY_USERNAME, "");
    }
    public String getPassword(){
        return userSharedPrefs.getString(Config.KEY_PASSWORD, "");
    }
    public String getToken(){
        return userSharedPrefs.getString(Config.KEY_TOKEN, "");
    }

    //---------------------------SETTERS-----------------------//
    public void setUserName(String name){
        prefsEditor.putString(Config.KEY_USERNAME, name).commit();
    }
    public void setEmail(String email)
    {
        prefsEditor.putString(Config.KEY_EMAIL, email).commit();
    }
    public void setPassword(String password)
    {
        prefsEditor.putString(Config.KEY_PASSWORD, password).commit();
    }
    public void setToken(String token)
    {
        prefsEditor.putString(Config.KEY_TOKEN, token).commit();
    }

}
