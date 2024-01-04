package com.poploli.onlinefilmreviewsystem.dto;

import java.math.BigDecimal;

public class EditReviewDTO {
    private Long reviewID;

    private String content;
    private BigDecimal score;

    public Long getReviewID() {
        return reviewID;
    }

    public void setReviewID(Long reviewID) {
        this.reviewID = reviewID;
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
