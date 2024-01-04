package com.poploli.onlinefilmreviewsystem.controller;

import com.poploli.onlinefilmreviewsystem.dto.LoginDTO;
import com.poploli.onlinefilmreviewsystem.model.User;
import com.poploli.onlinefilmreviewsystem.service.AdminService;
import com.poploli.onlinefilmreviewsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        if (!model.containsAttribute("loginError")) {
            model.addAttribute("loginError", "");
        }
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute LoginDTO loginDTO,
                               @RequestParam(defaultValue = "false") boolean isAdmin,
                               HttpSession session, @RequestParam(required = false) String redirect, RedirectAttributes redirectAttributes) {

        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        if (isAdmin) {
            adminService.validateAdmin(username, password);
            session.setAttribute("admin", username);
            session.setAttribute("user", username);
            return "redirect:/admin";
        } else {
            userService.validateLogin(username, password);
            session.setAttribute("user", username);
            return "redirect:/index";
        }
    }


}
