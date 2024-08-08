package com.upchina.spring.quartz.sotre;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by anjunli on  2023/3/15
 **/
//@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1","group1")
                .withDescription("任务描述：HelloJob 示例")
                //每次任务执行之后进行存储
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(){
        return TriggerBuilder.newTrigger()
                .withIdentity("tri1","group1")
                .withDescription("描述： 每5秒执行一次")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?").withMisfireHandlingInstructionFireAndProceed())
                .forJob(jobDetail())
                .build();
    }
}
