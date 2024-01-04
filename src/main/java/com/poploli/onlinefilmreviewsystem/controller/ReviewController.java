package com.poploli.onlinefilmreviewsystem.controller;

import com.poploli.onlinefilmreviewsystem.dto.ReviewDTO;
import com.poploli.onlinefilmreviewsystem.exception.SessionNotFoundException;
import com.poploli.onlinefilmreviewsystem.service.MovieService;
import com.poploli.onlinefilmreviewsystem.service.ReviewService;
import com.poploli.onlinefilmreviewsystem.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieService movieService;


    @PostMapping("/addReview")
    public String addReview(@ModelAttribute("ReviewDTO") ReviewDTO reviewDTO, HttpSession session, HttpServletRequest request) {
        SessionUtil.checkSession(session);
        String username = (String) session.getAttribute("user");
        reviewService.addReview(reviewDTO, username);
        return "redirect:/movie/" + reviewDTO.getMovieID();
    }


}