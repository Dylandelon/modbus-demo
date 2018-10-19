package com.dylan.modbusdemo.job;

import com.dylan.beans.DmsDevicControlbyCIMVo;
import com.dylan.beans.DmsDeviceEntity;
import com.dylan.modbusdemo.contants.ConstantInter;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Log4j
@Component
public class MyJobSchYali {
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

    @Async
    @Scheduled(cron = "0/1 * * * * *")
    public void reportCurrentTimeCron() throws InterruptedException {
        System.out.println(String.format("cron+++第%s次执行，当前时间为：%s", count2++, LocalDateTime.now()));
        long startime = System.currentTimeMillis();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
//        String url = "http://10.39.10.181:8240/meta/dmsDevicControlbyCIM";
        String url = "http://10.39.10.181:8240/meta/dmsDevicControlbyCIM";
        DmsDevicControlbyCIMVo entity = new DmsDevicControlbyCIMVo();
        entity.setId((int)System.currentTimeMillis()/1000);
//
//        entity.setCimDeviceId("GSB01");
//        entity.setCimDomain("HPS");
//        entity.setCimPoint("PAs");
//        entity.setCimStation("CA51PS01");
////        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.fff");
//        entity.setSendTime(LocalDateTime.now());
//        entity.setToken("1538026245");
//        entity.setUser("0");
//        entity.setValue("20");
//        entity.setRemark("jilu");
        entity.setCimDeviceId("CVAL01");
        entity.setCimDomain("HPS");
        entity.setCimPoint("POSmanualSet");
        entity.setCimStation("CA99PS01");
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.fff");
        entity.setSendTime(LocalDateTime.now());
        entity.setToken("1538989570");
        entity.setUser("0");
        entity.setValue("10000");
        entity.setRemark("jilu");


        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DmsDeviceEntity> httpEntity = new HttpEntity(entity, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        System.out.println(System.currentTimeMillis()-startime);
        log.info(responseEntity.getBody());

    }
}
