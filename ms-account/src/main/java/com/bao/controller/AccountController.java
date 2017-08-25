package com.bao.controller;

import com.alibaba.fastjson.JSON;
import com.bao.domain.Participant;
import com.bao.domain.Response;
import com.bao.model.FrozenRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * Created by nannan on 2017/8/24.
 */
@Service
@Slf4j
public class AccountController {

    @PostMapping("/v1/frozen")
    public Response<Participant> frozen(@RequestBody FrozenRequest request) {
        log.info("frozen start : {}", JSON.toJSONString(request));

        //商品库存进行冻结，并对冻结的记录进行存库，记录此次事件Id
        Participant participant = Participant.builder().serverName("ms-account").serverUri("/v1/" + UUID.randomUUID() + "/confirm").expireTime(LocalDate.now().plus(30L, ChronoUnit.MINUTES)).build();

        //注意，在此处将此事件抛给定时任务处理，30分钟后自动失效解冻

        log.info("frozen end");
        return Response.success(participant);
    }

    @PutMapping("/v1/tcc/{tccId}")
    public Response confirm(@PathVariable("tccId") String tccId) {
        log.info("confirm start : {}", tccId);
        //商品库存根据冻结状况进行扣减（不得超出规定时间）
        log.info("confirm end");
        return Response.success(null);
    }

    @DeleteMapping("/v1/tcc/{tccId}")
    public Response cancel(@PathVariable("tccId") String tccId) {
        log.info("cancel start : {}", tccId);
        //商品库存根据冻结状况进行回溯
        log.info("cancel end");
        return Response.success(null);
    }

}
