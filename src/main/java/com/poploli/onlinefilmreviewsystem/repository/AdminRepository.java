package com.poploli.onlinefilmreviewsystem.repository;

import com.poploli.onlinefilmreviewsystem.model.User;
import com.poploli.onlinefilmreviewsystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {


    Optional<Admin> findByAdminNameAndPassword(String adminname, String password);

}

