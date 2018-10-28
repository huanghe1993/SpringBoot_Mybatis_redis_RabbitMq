package com.huanghe.springboot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Author: River
 * @Date:Created in  9:33 2018/10/28
 * @Description: 定时任务
 */
@Service
public class ScheduledService {


    /**
     * second  minute, hour, day of month, month and day of week.
     * 0 * * * * MON-FRI" : 周一到周五的0秒的时候启动
     *
     */
    @Scheduled(cron = "0 * * * * MON-FRI")
    public void hello(){
        System.out.println("定时任务。。。");
    }

}
