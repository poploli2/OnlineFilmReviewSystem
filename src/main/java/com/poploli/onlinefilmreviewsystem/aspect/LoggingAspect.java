package com.poploli.onlinefilmreviewsystem.aspect;

import com.poploli.onlinefilmreviewsystem.model.AccessLog;
import com.poploli.onlinefilmreviewsystem.model.User;
import com.poploli.onlinefilmreviewsystem.repository.AccessLogRepository;
import com.poploli.onlinefilmreviewsystem.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpSession;

import java.sql.Timestamp;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Autowired
    private UserService userService;

    // 定义切点，拦截controller包下的所有方法
    @Pointcut("execution(* com.poploli.onlinefilmreviewsystem.controller..*(..))")
    public void controllerPointcut() {}

    // 方法返回后执行
    @AfterReturning("controllerPointcut()")
    public void logAfterReturning(JoinPoint joinPoint) {
        logActivity(joinPoint, "SUCCESS");
    }

    // 方法抛出异常后执行
    @AfterThrowing(pointcut = "controllerPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logActivity(joinPoint, "FAILED: " + e.getMessage());
    }

    private void logActivity(JoinPoint joinPoint, String status) {
        AccessLog log = new AccessLog();
        log.setOperation(joinPoint.getSignature().toShortString() + " - " + status);
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));

        // 获取HttpSession
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(false);

        // 如果session不为空，并且存在user属性，则记录用户
        if (session != null && session.getAttribute("user") != null) {
            String username = (String) session.getAttribute("user");
            User user = userService.findByUsername(username); // 假设UserService有findByUsername方法
            log.setUser(user);
        }

        accessLogRepository.save(log);
    }
}
