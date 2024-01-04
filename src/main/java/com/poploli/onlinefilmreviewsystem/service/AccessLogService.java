package com.poploli.onlinefilmreviewsystem.service;

import com.poploli.onlinefilmreviewsystem.exception.InvalidCredentialsException;
import com.poploli.onlinefilmreviewsystem.model.AccessLog;
import com.poploli.onlinefilmreviewsystem.model.Admin;
import com.poploli.onlinefilmreviewsystem.repository.AccessLogRepository;
import com.poploli.onlinefilmreviewsystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessLogService {
    @Autowired
    private AccessLogRepository accessLogRepository;

    public List<AccessLog> showAccessLogs(){
        return accessLogRepository.findTop100ByOrderByTimestampDesc();
    }


}
