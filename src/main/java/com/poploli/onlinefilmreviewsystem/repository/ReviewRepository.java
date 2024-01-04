package com.poploli.onlinefilmreviewsystem.repository;

import com.poploli.onlinefilmreviewsystem.model.Movie;
import com.poploli.onlinefilmreviewsystem.model.Review;
import com.poploli.onlinefilmreviewsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository  extends JpaRepository<Review, Long> {

    List<Review> findTop5ByMovie_MovieIDOrderByTimestampDesc(Long movieID);

    List<Review> findAllByMovie_MovieID(Long movieID);

    List<Review> findAllByUser_Username(String username);

    @Query("SELECT r.user.username FROM Review r WHERE r.reviewID = :reviewId")
    Optional<String> findUsernameByReviewId(@Param("reviewId") Long reviewId);

    boolean existsByUserAndMovie(User user, Movie movie);
}
