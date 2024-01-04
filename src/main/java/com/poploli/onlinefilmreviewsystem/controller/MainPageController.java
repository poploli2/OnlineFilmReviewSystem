package com.poploli.onlinefilmreviewsystem.controller;


import com.poploli.onlinefilmreviewsystem.exception.SessionNotFoundException;
import com.poploli.onlinefilmreviewsystem.model.Movie;
import com.poploli.onlinefilmreviewsystem.service.MovieService;
import com.poploli.onlinefilmreviewsystem.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private MovieService movieService;


    @GetMapping(value = {"/index", "/"})
    public String showMainPage(Model model, HttpSession session) {
        SessionUtil.checkSession(session);
        List<Movie> trendingMovies = movieService.getMostPopularMovies();
        List<Movie> latestMovies = movieService.getLatestMovies();
        List<Movie> topRatedMovies = movieService.getTopRatedMovies();


        model.addAttribute("trendingMovies", trendingMovies);
        model.addAttribute("latestMovies", latestMovies);
        model.addAttribute("topRatedMovies", topRatedMovies);

        return "index";
    }
}
