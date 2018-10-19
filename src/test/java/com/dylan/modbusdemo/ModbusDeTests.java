package com.dylan.modbusdemo;

import com.dylan.beans.DmsDevicControlbyCIMVo;
import com.dylan.beans.DmsDeviceEntity;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Log4j
@SpringBootTest
public class ModbusDeTests {

    @Test
    public void contextLoads() {
        long startime = System.currentTimeMillis();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://10.39.10.181:8240/meta/dmsDevicControlbyCIM";
//        String url = "http://localhost:8240/meta/dmsDevicControlbyCIM";
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
