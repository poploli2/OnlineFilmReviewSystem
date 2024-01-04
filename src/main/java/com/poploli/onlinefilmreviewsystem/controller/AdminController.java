package com.poploli.onlinefilmreviewsystem.controller;

import com.poploli.onlinefilmreviewsystem.dto.MovieDTO;
import com.poploli.onlinefilmreviewsystem.exception.SessionNotFoundException;
import com.poploli.onlinefilmreviewsystem.model.Movie;
import com.poploli.onlinefilmreviewsystem.model.User;
import com.poploli.onlinefilmreviewsystem.repository.AccessLogRepository;
import com.poploli.onlinefilmreviewsystem.service.FileStorageService;
import com.poploli.onlinefilmreviewsystem.service.MovieService;
import com.poploli.onlinefilmreviewsystem.service.ReviewService;
import com.poploli.onlinefilmreviewsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AccessLogRepository accessLogRepository;
    private void checkSession(HttpSession session) {
        if (session.getAttribute("admin") == null) {
            throw new SessionNotFoundException("会话不存在或用户未登录");
        }
    }
    @GetMapping
    public String adminHome(HttpSession session, Model model) {
        checkSession(session);
        model.addAttribute("users", userService.getUsersForApproval());
        model.addAttribute("movieDTO", new MovieDTO());
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("reviews",reviewService.getAllReviews());
        model.addAttribute("AccessLogs",accessLogRepository.findTop100ByOrderByTimestampDesc());
        return "adminHome";
    }

    @PostMapping("/approveUser")
    public String approveUser(@RequestParam Long userID, HttpSession session) {
        checkSession(session);
        userService.approveUser(userID);
        return "redirect:/admin";
    }



    @PostMapping("/addMovie")
    public String addMovie(@ModelAttribute MovieDTO movieDTO,
                           @RequestParam("image") MultipartFile image,
                           HttpSession session) {
        checkSession(session);

        Movie movie = movieService.addMovie(movieDTO);
        fileStorageService.storeFile(image, movie.getMovieID());

        return "redirect:/admin";
    }


    @PostMapping("/deleteMovie/{movieID}")
    public String deleteMovie(@PathVariable Long movieID, HttpSession session) {
        checkSession(session);
        movieService.deleteMovie(movieID);
        return "redirect:/admin";
    }

    @PostMapping("/deleteReview/{reviewID}")
    public String deleteReview(@PathVariable Long reviewID, HttpSession session) {
        checkSession(session);
        reviewService.deleteReview(reviewID);
        return "redirect:/admin";
    }
}