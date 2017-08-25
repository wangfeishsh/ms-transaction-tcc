package com.bao.service;

import com.bao.scheduler.UnfreezeTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * Created by nannan on 2017/8/25.
 */
@Component
public class UnfreezeService {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Scheduled(fixedDelay = 1000L)
    public void run(){
        executorService.execute(new UnfreezeTask());
    }
}
