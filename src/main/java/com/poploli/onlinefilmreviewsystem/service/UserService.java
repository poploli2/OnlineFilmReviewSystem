package com.poploli.onlinefilmreviewsystem.service;

import com.poploli.onlinefilmreviewsystem.dto.UserRegistrationDTO;
import com.poploli.onlinefilmreviewsystem.exception.InvalidCredentialsException;
import com.poploli.onlinefilmreviewsystem.exception.UserAlreadyExistsException;
import com.poploli.onlinefilmreviewsystem.exception.UserNotApprovedException;
import com.poploli.onlinefilmreviewsystem.exception.UserNotFoundException;
import com.poploli.onlinefilmreviewsystem.model.Admin;
import com.poploli.onlinefilmreviewsystem.model.User;
import com.poploli.onlinefilmreviewsystem.repository.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (isUsernameExists(userRegistrationDTO.getUsername())) {
            throw new UserAlreadyExistsException("用户名已被注册");
        }

        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(userRegistrationDTO.getPassword());
        userRepository.save(user);
    }

    public void validateLogin(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("用户名或密码不正确");
        }
        if (!user.isApprovalStatus()) {
            throw new UserNotApprovedException("用户名审核未通过");
        }
    }

    public void changeUserPassword(String username, String oldPassword, String newPassword) {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UserNotFoundException("用户未找到");
            }
            if (!user.getPassword().equals(oldPassword)) {

                throw new InvalidCredentialsException("旧密码不正确");
            }
            user.setPassword(newPassword);
            userRepository.save(user);
        } catch (InvalidCredentialsException | UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("更改密码时发生错误", e);
        }
    }


    public boolean validateUser(String username, String password){
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);

        return user.isPresent();
    }
    public boolean isApproved(String username){
        User user = userRepository.findByUsername(username);
        return user.isApprovalStatus();
    }

    public void updateUserPassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setPassword(newPassword);

            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }
    public List<User> getUsersForApproval() {
        return userRepository.findByApprovalStatus(false);
    }

    // 审批用户
    public void approveUser(Long userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("用户未找到"));
        user.setApprovalStatus(true);
        userRepository.save(user);
    }


    public boolean isUsernameExists(String username) {
            User user = userRepository.findByUsername(username);
            return user != null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
