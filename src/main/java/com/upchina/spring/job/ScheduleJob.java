package com.upchina.spring.job;

import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description Spring定时任务
 * @Date 2020/8/27  16:21
 **/
//@Component
public class ScheduleJob {
    SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

//    @Scheduled(cron = "0/5 * * * * ?")
    //使用配置文件中的表达式
      @Scheduled(cron = "${scheduler.time}")
//    @Scheduled(fixedDelay = 5*1000)
//    @Scheduled(fixedRate = 5*1000)
    public void scheDemo(){
        System.out.println("定时任务，每5秒执行一次"+simpleDateFormat.format(new Date()));
    }
}
