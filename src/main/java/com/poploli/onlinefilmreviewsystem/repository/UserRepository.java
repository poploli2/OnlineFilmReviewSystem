package com.poploli.onlinefilmreviewsystem.repository;

import com.poploli.onlinefilmreviewsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByApprovalStatus(Boolean approvalStatus);

    Optional<User> findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

}

