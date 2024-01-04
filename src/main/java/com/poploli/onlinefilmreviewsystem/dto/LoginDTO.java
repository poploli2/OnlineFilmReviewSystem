package com.poploli.onlinefilmreviewsystem.dto;

public class LoginDTO {

    private String username;
    private String password;
    private String isAdmin;


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

    public String isAdmin() {
        return isAdmin;
    }

    public void setAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }


}
