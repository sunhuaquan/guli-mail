package com.sun.mail.produce.servcie.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sun.mail.produce.servcie.CatalogService;
import com.sun.mail.produce.vo.Catalog2Vo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService {

    private static final String catalogJson="catalogJson";

    private static final String catalogLock="catalogLock";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;


    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJson() {

        String json = redisTemplate.opsForValue().get(catalogJson);
        if(StringUtils.isBlank(json)){

            Map<String, List<Catalog2Vo>> catalogJsonDbWithRedisLock = getCatalogJsonDbWithRedisLock();
            String s = JSON.toJSONString(catalogJsonDbWithRedisLock);
            redisTemplate.opsForValue().set(catalogJson,s);
            System.out.println("缓存命中...直接返回...");
            return catalogJsonDbWithRedisLock;

        }
        //转为指定的对象
        Map<String, List<Catalog2Vo>> result = JSON.parseObject(json,new TypeReference<Map<String, List<Catalog2Vo>>>(){});
        return result;
    }

    private  Map<String, List<Catalog2Vo>> getCatalogJsonDbWithRedisson(){

        RLock lock = redissonClient.getLock(catalogLock);
        lock.lock();
        try {
            Map<String, List<Catalog2Vo>> categoryMap = getCategoryMap();
            return categoryMap;

        }finally {
            lock.unlock();
        }
    }


    private  Map<String, List<Catalog2Vo>> getCatalogJsonDbWithRedisLock(){

        String uuid = UUID.randomUUID().toString();
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Boolean lock = ops.setIfAbsent("lock", uuid,500, TimeUnit.SECONDS);
        if (lock) {
            Map<String, List<Catalog2Vo>> categoriesDb;
            try {
                categoriesDb = getCategoryMap();
            }finally {
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                //删除锁
                redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList("lock"), uuid);
            }
            return categoriesDb;
        }else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 睡眠0.1s后，重新调用 //自旋
            return getCatalogJsonDbWithRedisLock();
        }


    }

    private Map<String, List<Catalog2Vo>> getCategoryMap() {

        log.info("---------------------------getCategoryMap-------------------------------------");
        Map<String, List<Catalog2Vo>> result= new HashMap<>();
        result.put("111",Arrays.asList(new Catalog2Vo(1L)));
        return result;
    }
}
