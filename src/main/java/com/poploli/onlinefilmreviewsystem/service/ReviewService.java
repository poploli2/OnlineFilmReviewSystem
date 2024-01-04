package com.poploli.onlinefilmreviewsystem.service;

import com.poploli.onlinefilmreviewsystem.dto.EditReviewDTO;
import com.poploli.onlinefilmreviewsystem.dto.ReviewDTO;
import com.poploli.onlinefilmreviewsystem.exception.DuplicateReviewException;
import com.poploli.onlinefilmreviewsystem.exception.MovieNotFoundException;
import com.poploli.onlinefilmreviewsystem.exception.ReviewNotFoundException;
import com.poploli.onlinefilmreviewsystem.exception.UnauthorizedAccessException;
import com.poploli.onlinefilmreviewsystem.model.Movie;
import com.poploli.onlinefilmreviewsystem.model.Review;
import com.poploli.onlinefilmreviewsystem.model.User;
import com.poploli.onlinefilmreviewsystem.repository.MovieRepository;
import com.poploli.onlinefilmreviewsystem.repository.ReviewRepository;

import com.poploli.onlinefilmreviewsystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    public List<Review> getRecentReviewsByMovieId(Long movieId) {
        return reviewRepository.findTop5ByMovie_MovieIDOrderByTimestampDesc(movieId);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public void addReview(ReviewDTO reviewDTO, String username) {
        User user = userRepository.findByUsername(username);
        Movie movie = movieRepository.findById(reviewDTO.getMovieID())
                .orElseThrow(() -> new MovieNotFoundException("电影未找到"));

        boolean alreadyReviewed = reviewRepository.existsByUserAndMovie(user, movie);
        if (alreadyReviewed) {
            throw new DuplicateReviewException("您已经对这部电影发表过评论");
        }

        Review review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setContent(reviewDTO.getContent());
        review.setScore(reviewDTO.getScore());
        review.setTimestamp(new Timestamp(System.currentTimeMillis()));

        reviewRepository.save(review);

        movieService.updateMovieRating(movie.getMovieID()); // 更新电影评分
        movieService.updateMoviePopularity(movie.getMovieID()); // 更新电影热度
    }
    public List<Review> getReviewsByUsername(String username){
        return reviewRepository.findAllByUser_Username(username);
    }
    public void editReview(EditReviewDTO reviewDTO, String username) {
        Review existingReview = reviewRepository.findById(reviewDTO.getReviewID())
                .orElseThrow(() -> new EntityNotFoundException("评论未找到"));

        if (!existingReview.getUser().getUsername().equals(username)) {
            throw new UnauthorizedAccessException("用户无权编辑此评论");
        }

        // 更新评论内容
        existingReview.setContent(reviewDTO.getContent());
        existingReview.setScore(reviewDTO.getScore());
        existingReview.setTimestamp(new Timestamp(System.currentTimeMillis()));
        reviewRepository.save(existingReview);

        // 更新电影评分和热度
        movieService.updateMovieRating(existingReview.getMovie().getMovieID());
        movieService.updateMoviePopularity(existingReview.getMovie().getMovieID());
    }
    public void deleteReview(Long reviewID) {
        Review review = reviewRepository.findById(reviewID)
                .orElseThrow(() -> new ReviewNotFoundException("评论未找到"));
        reviewRepository.deleteById(reviewID);
        Long movieId = review.getMovie().getMovieID();
        movieService.updateMovieRating(movieId);// 更新相关电影的评分
        movieService.updateMoviePopularity(movieId);  // 更新电影热度
    }

    public void deleteReview(Long reviewID, String username) {
        Review review = reviewRepository.findById(reviewID)
                .orElseThrow(() -> new EntityNotFoundException("评论未找到"));
        Optional<String> reviewUsername = reviewRepository.findUsernameByReviewId(reviewID);
        if (reviewUsername.isEmpty() || !reviewUsername.get().equals(username)) {
            throw new UnauthorizedAccessException("用户无权删除此评论");
        }

        // 删除评论
        reviewRepository.deleteById(reviewID);

        // 更新电影评分和热度
        movieService.updateMovieRating(review.getMovie().getMovieID());
        movieService.updateMoviePopularity(review.getMovie().getMovieID());
    }


    public void deleteReviewsByMovieId(Long movieID) {
        List<Review> reviews = reviewRepository.findAllByMovie_MovieID(movieID);
        for (Review review : reviews) {
            reviewRepository.delete(review);
        }
    }
}
