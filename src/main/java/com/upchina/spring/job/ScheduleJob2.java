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
        System.out.println("ScheduleJob1 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest2() {
        System.out.println("ScheduleJob2 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest3() {
        System.out.println("ScheduleJob3 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest4() {
        System.out.println("ScheduleJob4 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest5() {
        System.out.println("ScheduleJob5 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest6() {
        System.out.println("ScheduleJob6 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest7() {
        System.out.println("ScheduleJob7 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest8() {
        System.out.println("ScheduleJob8 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest9() {
        System.out.println("ScheduleJob9 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest10() {
        System.out.println("ScheduleJob10 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest11() {
        System.out.println("ScheduleJob11 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest12() {
        System.out.println("ScheduleJob12 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest13() {
        System.out.println("ScheduleJob13 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest14() {
        System.out.println("ScheduleJob14 定时任务： " + new Date());
    }

    @Scheduled(cron = "${schedule.time}")
    public void scheduleTest15() {
        System.out.println("ScheduleJob15 定时任务： " + new Date());
    }
}
