package com.mthree.LoginDAO;

public class Login {
    private String username;
    private String password;
    private String typeofuser;

    public Login() {
    }

    public Login(String username, String password, String typeofuser) {
        this.username = username;
        this.password = password;
        this.typeofuser = typeofuser;
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

    public String getTypeofuser() {
        return typeofuser;
    }

    public void setTypeofuser(String typeofuser) {
        this.typeofuser = typeofuser;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", typeofuser='" + typeofuser + '\'' +
                '}';
    }
}
