package com.poploli.onlinefilmreviewsystem.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MovieDTO {
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private String introduction;

    private String actor;

    private String director;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    public String getDirector() {
        return director;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getActor() {
        return actor;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
