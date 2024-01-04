package com.poploli.onlinefilmreviewsystem.model;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdminID")
    private Long adminID;

    @Column(name = "Adminname", unique = true, nullable = false)
    private String adminName;

    @Column(name = "Password", nullable = false)
    private String password;


    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
