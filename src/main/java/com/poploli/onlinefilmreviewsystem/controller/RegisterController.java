package com.poploli.onlinefilmreviewsystem.controller;

import com.poploli.onlinefilmreviewsystem.dto.UserRegistrationDTO;
import com.poploli.onlinefilmreviewsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        if (!model.containsAttribute("registrationError")) {
//            model.addAttribute("registrationError", "");
            model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userRegistrationDTO") UserRegistrationDTO userRegistrationDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        userService.registerUser(userRegistrationDTO);
        return "redirect:/login";
    }

}
