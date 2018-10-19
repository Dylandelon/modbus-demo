package com.dylan.modbusdemo.job;

import com.dylan.modbusdemo.contants.ConstantInter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyJobSch {
    private Integer count0 = 1;
    private Integer count1 = 1;
    private Integer count2 = 1;

    private Integer count3 = 1;
    private Integer count4 = 1;

//    @Async
//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws InterruptedException {

        System.out.println(String.format("fixedRate---第%s次执行，当前时间为：%s", count0++, LocalDateTime.now()));
        ConstantInter.lock.lock();
        Thread.sleep(6*1000);
        System.out.println(String.format("fixedRate---第%s次执行，当前时间为：%s", count3++, LocalDateTime.now()));

        ConstantInter.condition.await();
        Thread.sleep(6*1000);
        ConstantInter.lock.unlock();
    }

//    @Async
//    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTimeAfterSleep() throws InterruptedException {
        System.out.println(String.format("fixedDelay===第%s次执行，当前时间为：%s", count1++, LocalDateTime.now()));
        ConstantInter.lock.lock();
        Thread.sleep(6*1000);
        System.out.println(String.format("fixedDelay===第%s次执行，当前时间为：%s", count4++, LocalDateTime.now()));
        ConstantInter.condition.signal();
        Thread.sleep(6*1000);
        ConstantInter.lock.unlock();
    }

//    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void reportCurrentTimeCron() throws InterruptedException {
        System.out.println(String.format("cron+++第%s次执行，当前时间为：%s", count2++, LocalDateTime.now()));
        if(count2 <3){
            Thread.sleep(10*1000);
        }

    }
}
