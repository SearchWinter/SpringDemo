package com.upchina.spring.quartz.sotre;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by anjunli on  2023/3/15
 **/
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("现在时间是："+new Date());
    }
}
