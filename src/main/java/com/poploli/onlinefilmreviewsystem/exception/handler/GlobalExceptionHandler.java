package com.poploli.onlinefilmreviewsystem.exception.handler;

import com.poploli.onlinefilmreviewsystem.exception.*;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SessionNotFoundException.class)
    public String handleSessionNotFound(SessionNotFoundException ex) {
        return "redirect:/login";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExists(UserAlreadyExistsException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("registrationError", ex.getMessage());
        return "redirect:/register";
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public String handleInvalidCredentials(InvalidCredentialsException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("loginError", ex.getMessage());
        return "redirect:/login";
    }

    @ExceptionHandler(UserNotApprovedException.class)
    public String handleUserNotApproved(UserNotApprovedException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("loginError", ex.getMessage());
        return "redirect:/login";
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public String handleMovieNotFound(MovieNotFoundException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(InvalidSortModeException.class)
    public String handleInvalidSortMode(InvalidSortModeException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(DuplicateReviewException.class)
    public String handleDuplicateReview(DuplicateReviewException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound(UserNotFoundException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public String handleReviewNotFound(UserNotFoundException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(ServiceException.class)
    public String handleServiceException(ServiceException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public String handleUnauthorizedAccess(UnauthorizedAccessException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/error";
    }
}

