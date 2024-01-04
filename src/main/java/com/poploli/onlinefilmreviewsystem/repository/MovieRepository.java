package com.poploli.onlinefilmreviewsystem.repository;

import com.poploli.onlinefilmreviewsystem.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findTop12ByOrderByPopularityDesc();

    List<Movie> findTop12ByOrderByReleaseDateDesc();

    List<Movie> findTop12ByOrderByRatingDesc();
    List<Movie> findByOrderByPopularityDesc();

    List<Movie> findByOrderByReleaseDateDesc();

    List<Movie> findByOrderByRatingDesc();

    Movie findMovieByMovieID(Long movieID);
    List<Movie> findByTitleContainingIgnoreCase(String titleKeyword);
}
