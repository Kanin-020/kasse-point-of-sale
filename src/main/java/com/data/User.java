package com.data;

public class User {

    protected String username;
    protected String password;
    protected String position;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.position = null;
    }

    public User(String username, String password, String position) {
        this.username = username;
        this.password = password;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", position=" + position + "]";
    }

}
