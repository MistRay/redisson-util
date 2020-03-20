package com.mistray;

import com.mistray.redisson.RedissonLockUtil;
import lombok.SneakyThrows;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
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
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            RLock lock = RedissonLockUtil.getLock("1231214");
            RFuture<Boolean> future = lock.tryLockAsync();
            System.out.println(111111);
            try {
                Thread.sleep(40000);
                System.out.println(future.isSuccess());
                if (future.isSuccess()) {
                    System.out.println("1111");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlockAsync();
            }
        }).start();

    }
}
