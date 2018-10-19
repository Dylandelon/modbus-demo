package com.dylan.modbusdemo.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyJob implements Runnable {
    private Integer count0 = 1;


//    @Scheduled(cron = "0/5 * * * * *")
    @Override
    public void run() {

        System.out.println(String.format("MyJob===第%s次执行，当前时间为：%s", count0++, LocalDateTime.now()));
        try {
            Thread.sleep(6*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
