package com.bao.controller;

import com.bao.domain.Participant;
import com.bao.domain.ParticipantRequest;
import com.bao.domain.Response;
import com.bao.service.CoordinatorService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by nannan on 2017/8/24.
 */
@Service
@Slf4j
public class CoordinatorController {

    @Autowired
    private CoordinatorService service;

    @PostMapping("/v1/confirm")
    public Response tccConfirm(@RequestBody ParticipantRequest request) {
        log.info("tccConfirm start : {}");
        service.tccConfirm(request);
        log.info("tccConfirm end");
        return null;
    }

    @PostMapping("/v1/cancel")
    public Response tccCancel(@RequestBody ParticipantRequest request) {
        log.info("tccCancel start : {}");
        service.tccConfirm(request);
        log.info("tccCancel end");
        return null;
    }

}
