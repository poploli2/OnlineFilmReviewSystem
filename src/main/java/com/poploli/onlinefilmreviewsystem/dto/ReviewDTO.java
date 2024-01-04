package com.poploli.onlinefilmreviewsystem.dto;

import java.math.BigDecimal;

public class ReviewDTO {
    private Long movieID;
    private String content;
    private BigDecimal score;

    // Getter and setter methods
    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
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
}
