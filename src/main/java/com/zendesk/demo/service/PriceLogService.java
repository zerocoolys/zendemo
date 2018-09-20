package com.zendesk.demo.service;

import com.zendesk.demo.model.PriceLog;
import com.zendesk.demo.repo.PriceLogRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class PriceLogService {

    private final Logger LOGGER = getLogger(PriceLogService.class);

    @Autowired
    private PriceLogRepo priceLogRepo;

    public PriceLog createPriceLog(long userId, double price) {
        return priceLogRepo.save(new PriceLog().setUserId(userId).setPrice(price).setTime(new Date()));
    }

    
}
