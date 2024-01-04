package com.poploli.onlinefilmreviewsystem.service;

import com.poploli.onlinefilmreviewsystem.exception.InvalidCredentialsException;
import com.poploli.onlinefilmreviewsystem.model.Admin;
import com.poploli.onlinefilmreviewsystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public void validateAdmin(String adminName, String password){
        Optional<Admin> admin = adminRepository.findByAdminNameAndPassword(adminName, password);
        if (!admin.isPresent()) {
            throw new InvalidCredentialsException("管理员用户名或密码不正确");
        }
    }


}
