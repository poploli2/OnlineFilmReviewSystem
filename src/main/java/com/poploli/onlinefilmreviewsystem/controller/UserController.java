package com.poploli.onlinefilmreviewsystem.controller;

import com.poploli.onlinefilmreviewsystem.dto.EditReviewDTO;
import com.poploli.onlinefilmreviewsystem.dto.PasswordChangeDTO;
import com.poploli.onlinefilmreviewsystem.exception.InvalidCredentialsException;
import com.poploli.onlinefilmreviewsystem.exception.SessionNotFoundException;
import com.poploli.onlinefilmreviewsystem.exception.UserNotFoundException;
import com.poploli.onlinefilmreviewsystem.model.User;
import com.poploli.onlinefilmreviewsystem.service.ReviewService;
import com.poploli.onlinefilmreviewsystem.service.UserService;
import com.poploli.onlinefilmreviewsystem.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;



    @GetMapping
    public String getUserHome(HttpSession session, Model model){
        SessionUtil.checkSession(session);
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("passwordChangeDTO", new PasswordChangeDTO());
        model.addAttribute("reviews",reviewService.getReviewsByUsername((String) session.getAttribute("user")));
        return "userHome";
    }

    @PostMapping("/changePassword")
    public String changeUserPassword(@ModelAttribute("passwordChangeDTO") PasswordChangeDTO passwordChangeDTO, HttpSession session, Model model) {
        SessionUtil.checkSession(session);
        String username = (String) session.getAttribute("user");

        try {
            userService.changeUserPassword(username, passwordChangeDTO.getOldPassword(), passwordChangeDTO.getNewPassword());
            model.addAttribute("successMessage", "密码修改成功!");
        } catch (InvalidCredentialsException | UserNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (ServiceException e) {
            // 处理其他服务异常
            model.addAttribute("errorMessage", "服务异常，请稍后重试");
        }

        return "userHome";
    }

    @PostMapping("/editReview")
    public String editReview(@ModelAttribute("EditReviewDTO") EditReviewDTO editReviewDTO,@RequestParam("reviewId") Long reviewId, HttpSession session) {
        SessionUtil.checkSession(session);
        String username = ((String) session.getAttribute("user"));
        editReviewDTO.setReviewID(reviewId);
        reviewService.editReview(editReviewDTO, username);
        return "redirect:/user";
    }

    @PostMapping("/deleteReview")
    public String deleteReview(@RequestParam("reviewId") Long reviewId,HttpSession session) {
        SessionUtil.checkSession(session);
        String username = ((String) session.getAttribute("user"));
        reviewService.deleteReview(reviewId,username);
        return "redirect:/user";
    }


}
