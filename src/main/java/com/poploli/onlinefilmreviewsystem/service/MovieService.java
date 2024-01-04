package com.poploli.onlinefilmreviewsystem.service;

import com.poploli.onlinefilmreviewsystem.dto.MovieDTO;
import com.poploli.onlinefilmreviewsystem.exception.InvalidSortModeException;
import com.poploli.onlinefilmreviewsystem.exception.MovieNotFoundException;
import com.poploli.onlinefilmreviewsystem.model.Movie;
import com.poploli.onlinefilmreviewsystem.model.Review;
import com.poploli.onlinefilmreviewsystem.repository.MovieRepository;
import com.poploli.onlinefilmreviewsystem.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

//    @Autowired
//    public MovieService(@Lazy ReviewService reviewService) {
//        this.reviewService = reviewService;
//    }

    @Scheduled(cron = "0 0 1 * * *")  // 每天凌晨1点执行更新电影热度
    public void updatePopularityForAllMovies() {
        List<Movie> allMovies = movieRepository.findAll();
        for (Movie movie : allMovies) {
            updateMoviePopularity(movie.getMovieID());
        }
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    public Movie getMovieById(long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("电影未找到"));
    }

    public List<Movie> getTopRatedMovies() {
        return movieRepository.findTop12ByOrderByRatingDesc();
    }

    public List<Movie> getLatestMovies() {
        return movieRepository.findTop12ByOrderByReleaseDateDesc();
    }

    public List<Movie> getMostPopularMovies() {
        return movieRepository.findTop12ByOrderByPopularityDesc();
    }

    public List<Movie> getMoviesSorted(String sortMode) {
        switch (sortMode) {
            case "popularity":
                return getMostPopularMovies();
            case "time":
                return getLatestMovies();
            case "rating":
                return getTopRatedMovies();
            default:
                throw new InvalidSortModeException("无效的排序模式");
        }
    }

    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setIntroduction(movieDTO.getIntroduction());
        movie.setActor(movieDTO.getActor());
        movie.setDirector(movieDTO.getDirector());
//        movie.setRating(BigDecimal.ZERO);
//        movie.setViews(0);
//        movie.setPopularity(0);
        movie = movieRepository.save(movie);  // 保存电影并获取更新后的实例，其中包含了生成的 MovieID
        return movie;
    }

    public void deleteMovie(Long movieID) {
        Movie movie = movieRepository.findById(movieID)
                .orElseThrow(() -> new MovieNotFoundException("电影未找到"));
        reviewService.deleteReviewsByMovieId(movieID);
        movieRepository.delete(movie);
    }

    public void updateMovieRating(Long movieId) {
        List<Review> reviews = reviewRepository.findAllByMovie_MovieID(movieId);
        Movie movie = getMovieById(movieId);

        if (!reviews.isEmpty()) {
            BigDecimal averageRating = reviews.stream()
                    .map(Review::getScore)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(new BigDecimal(reviews.size()), 2, RoundingMode.HALF_UP); // 设置小数点后两位，使用四舍五入

            movie.setRating(averageRating);
        } else {
            // 如果没有评论，将评分设置为 null
            movie.setRating(null);
        }

        movieRepository.save(movie);
    }

    public void incrementMovieViews(Long movieID) {
        Movie movie = getMovieById(movieID); // 这里也会抛出异常
        movie.setViews(movie.getViews() + 1);
        movieRepository.save(movie);
        updateMoviePopularity(movieID);  // 更新热度
    }

    public void updateMoviePopularity(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        int popularity = calculatePopularity(movie);
        movie.setPopularity(popularity);

        movieRepository.save(movie);
    }

    private int calculatePopularity(Movie movie) {
        int views = movie.getViews();
        List<Review> reviews = reviewRepository.findAllByMovie_MovieID(movie.getMovieID());
        int commentsCount = reviews.size();

        long daysSinceRelease = (new Date().getTime() - movie.getReleaseDate().getTime()) / (1000 * 60 * 60 * 24);
        if (daysSinceRelease < 1) daysSinceRelease = 1;

        double popularity = (views * 1.0 + commentsCount * 5.0) / Math.log(daysSinceRelease);
        return (int) popularity;
    }

    // 获取按上映时间排序的所有电影列表
    public List<Movie> getMoviesByReleaseDate() {
        return movieRepository.findByOrderByReleaseDateDesc();
    }

    // 获取按热度排序的所有电影列表
    public List<Movie> getMoviesByPopularity() {
        return movieRepository.findByOrderByPopularityDesc();
    }

    // 获取按评分排序的所有电影列表
    public List<Movie> getMoviesByRating() {
        return movieRepository.findByOrderByRatingDesc();
    }
}
