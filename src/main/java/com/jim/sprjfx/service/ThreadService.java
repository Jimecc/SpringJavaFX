package com.jim.sprjfx.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


/**
 * Redis 操作
 */
@Component
@Slf4j
@RestController
public class ThreadService {

    @Autowired
    private AsyncTaskService asyncTaskService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String key;
    public String value;
    public boolean switchRedis = true;
    public boolean firstTime = true;

    @Async("taskExecutor")
    public void ScanRedis(String key) throws InterruptedException {

        if(firstTime) {
            SetRedis("hskcool", "coolhsk");
            this.firstTime = false ;
        }

        while (true) {
            if(switchRedis) {
                try {
                    Thread.sleep(300);
                    log.info("Redis查找成功---------" + "\tkey:" + key + "\tvalue:" + redisTemplate.opsForValue().get(key));
                    log.info("Redis删除成功---------" + "\tkey:" + key + "\tvalue:" + redisTemplate.delete(key));
                    log.info("Redis查找失败---------" + "\tkey:" + key + "\tvalue:" + redisTemplate.opsForValue().get(key));
                    System.out.println();
                    key = randomGen(8);
                    value = randomGen(10);
                    SetRedis(key, value);
                } catch (InterruptedException e) {
                    Thread.sleep(300);
                    e.printStackTrace();
                    log.info("Redis 出错--------" + e);
                }
            }
        }
    }

    public void switchRedis(){
        this.switchRedis = !this.switchRedis;
    }


    public String SetRedis(String key,String value){
        redisTemplate.opsForValue().set(key,value);
        return "Success";
    }


    public static String randomGen(int place) {
        String base = "qwertyuioplkjhgfdsazxcvbnmQAZWSXEDCRFVTGBYHNUJMIKLOP0123456789";
        StringBuffer sb = new StringBuffer();
        Random rd = new Random();
        for(int i=0;i<place;i++) {
            sb.append(base.charAt(rd.nextInt(base.length())));
        }
        return sb.toString();
    }

}

