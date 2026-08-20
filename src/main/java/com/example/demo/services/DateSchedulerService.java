package com.example.demo.services;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DateSchedulerService {

    @Scheduled(cron = "0 * * * * *")
    public void printDate() {
        System.out.println(new Date());
    }

}
