package com.poploli.onlinefilmreviewsystem.controller;

import com.poploli.onlinefilmreviewsystem.dto.ReviewDTO;
import com.poploli.onlinefilmreviewsystem.exception.SessionNotFoundException;
import com.poploli.onlinefilmreviewsystem.model.Movie;
import com.poploli.onlinefilmreviewsystem.model.Review;
import com.poploli.onlinefilmreviewsystem.service.MovieService;
import com.poploli.onlinefilmreviewsystem.service.ReviewService;
import com.poploli.onlinefilmreviewsystem.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/movie/{id}")
    public String getMovie(@PathVariable("id") Long movieId, Model model, HttpSession session) {
        SessionUtil.checkSession(session);
        Movie movie = movieService.getMovieById(movieId);
        movieService.incrementMovieViews(movieId);
        List<Review> recentReviews = reviewService.getRecentReviewsByMovieId(movieId);

        model.addAttribute("movie", movie);
        model.addAttribute("recentReviews", recentReviews);
        model.addAttribute("reviewDTO", new ReviewDTO());
        return "movie";
    }

    @GetMapping(value = {"/movies", "/movies/{sortMode}"})
    public String getMovies(@PathVariable Optional<String> sortMode, Model model, HttpSession session) {
        SessionUtil.checkSession(session);
        String mode = sortMode.orElse("popularity");
        List<Movie> sortedMovies = movieService.getMoviesSorted(mode);
        model.addAttribute("movies", sortedMovies);
        return "movies";
    }


}