package com.upchina.spring.job;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by anjunli on  2022/12/27
 **/
@Component
@ConditionalOnProperty(prefix = "schedule", name = "enabled", havingValue = "true")
public class ScheduleJob2 {

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest() {
        System.out.println("ScheduleJob2 定时任务： " + new Date());
    }
}
