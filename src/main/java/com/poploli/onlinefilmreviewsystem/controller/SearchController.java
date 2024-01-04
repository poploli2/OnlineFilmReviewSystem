package com.poploli.onlinefilmreviewsystem.controller;

import com.poploli.onlinefilmreviewsystem.exception.SessionNotFoundException;
import com.poploli.onlinefilmreviewsystem.model.Movie;
import com.poploli.onlinefilmreviewsystem.repository.MovieRepository;
import com.poploli.onlinefilmreviewsystem.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/search")
    public String searchMovies(@RequestParam String query, Model model, HttpSession session) {
        SessionUtil.checkSession(session);

        List<Movie> searchResults = movieRepository.findByTitleContainingIgnoreCase(query);


        model.addAttribute("searchResults", searchResults);
        model.addAttribute("query",query);
        return "search"; // 返回搜索结果的视图名称
    }
}
