package com.mistray;

import com.mistray.redisson.RedissonLockUtil;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ZJY(MistRay)
 * @Project redisson-study
 * @Package com.mistray
 * @create 2020年03月20日 10:41
 * @Desc
 */
@Component
public class EventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RedissonClient redissonClient;
    /**
     *
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        boolean tryLock = RedissonLockUtil.tryLock("1121122", TimeUnit.SECONDS, 2, 100);
        if (tryLock){
            System.out.println("111111222222");
        }
    }
}
