package com.poploli.onlinefilmreviewsystem.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovieID")
    private Long movieID;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Director", length = 255)
    private String director;

    @Column(name = "Actor", length = 255)
    private String actor;
    @Column(name = "Rating", precision = 3, scale = 1)
    private BigDecimal rating;

    @Column(name = "Release_Date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Column(name = "Views")
    private int views = 0;

    @Column(name = "Popularity")
    private int popularity;

    @Column(name = "Introduction", columnDefinition = "TEXT")
    private String introduction;


    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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



    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }


    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
