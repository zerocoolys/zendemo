package com.zendesk.demo.repo;

import com.zendesk.demo.model.PriceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceLogRepo extends JpaRepository<PriceLog, Long> {
}
