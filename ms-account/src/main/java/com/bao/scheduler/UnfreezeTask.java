package com.bao.scheduler;

import com.alibaba.fastjson.JSON;
import com.bao.model.FrozenDomain;
import lombok.extern.slf4j.Slf4j;
import sun.plugin2.message.JavaScriptBaseMessage;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by nannan on 2017/8/25.
 */
@Slf4j
public class UnfreezeTask implements Runnable {

    //Java提供的线程安全的Queue可以分为阻塞队列和非阻塞队列，其中阻塞队列的典型例子是BlockingQueue，非阻塞队列的典型例子是ConcurrentLinkedQueue，在实际应用中要根据实际需要选用阻塞队列或者非阻塞队列。
    public static ConcurrentLinkedQueue<FrozenDomain> linkedQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
        log.info("UnfreezeTask start");
        if (!linkedQueue.isEmpty()) {
            while (true) {
                FrozenDomain frozenDomain = linkedQueue.peek();
                log.info(JSON.toJSONString(frozenDomain));
                LocalDateTime now = LocalDateTime.now();
                if (now.isBefore(frozenDomain.getExpiration())) {
                    //进行解冻操作，记住，如果解冻失败，需进行特殊处理
                    linkedQueue.poll();
                } else {
                    break;
                }
            }

        }
        log.info("UnfreezeTask end");
    }
}
