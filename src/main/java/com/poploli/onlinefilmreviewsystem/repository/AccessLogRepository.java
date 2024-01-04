package com.poploli.onlinefilmreviewsystem.repository;

import com.poploli.onlinefilmreviewsystem.model.AccessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
    List<AccessLog> findTop100ByOrderByTimestampDesc();
}