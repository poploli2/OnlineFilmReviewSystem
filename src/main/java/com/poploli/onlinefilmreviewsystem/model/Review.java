package com.poploli.onlinefilmreviewsystem.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID;
    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "MovieID", nullable = false)
    private Movie movie;

    @Column(name = "Content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "Score", precision = 3, scale = 1)
    private BigDecimal score;

    @Column(name = "Timestamp")
    private Timestamp timestamp;

    public Long getReviewID() {
        return reviewID;
    }

    public void setReviewID(Long reviewID) {
        this.reviewID = reviewID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}