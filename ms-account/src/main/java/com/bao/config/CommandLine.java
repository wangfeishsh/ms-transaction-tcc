package com.bao.config;

import com.bao.model.FrozenDomain;
import com.bao.scheduler.UnfreezeTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by nannan on 2017/8/25.
 */
@Component
@Slf4j
public class CommandLine implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {

        log.info("==========");

        UnfreezeTask.linkedQueue.add(FrozenDomain.builder().tccId("1").expiration(LocalDateTime.now()).build());
        UnfreezeTask.linkedQueue.add(FrozenDomain.builder().tccId("2").expiration(LocalDateTime.now()).build());
        log.info("==========");
    }
}
