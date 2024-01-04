package com.poploli.onlinefilmreviewsystem.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
@Entity
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogID")
    private Long logID;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = true)
    private User user;

    @Column(name = "Operation", length = 255)
    private String operation;

    @Column(name = "Timestamp")
    private Timestamp timestamp;



    public Long getLogID() {
        return logID;
    }

    public void setLogID(Long logID) {
        this.logID = logID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}