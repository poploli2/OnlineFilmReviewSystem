package com.poploli.onlinefilmreviewsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class OnlineFilmReviewSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineFilmReviewSystemApplication.class, args);
    }

}
