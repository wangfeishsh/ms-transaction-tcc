package com.bao.service;

import com.bao.common.BusinessCode;
import com.bao.domain.Participant;
import com.bao.domain.ParticipantRequest;
import com.bao.domain.Response;
import com.bao.exception.BaseException;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
public class CoordinatorService {

    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private RestTemplate restTemplate;

    //确认资源
    public void tccConfirm(@RequestBody ParticipantRequest request) {
        List<Participant> list = request.getParticipants();
        List<Participant> success = request.getParticipants();
        if (!CollectionUtils.isEmpty(list)) {
            for (Participant node : list) {
                InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(node.getServerName(), Boolean.FALSE);
                String url = instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + node.getServerUri();
                ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity(node.getData()), Response.class);
                if (!response.getBody().isSuccess()) {
                    log.error("confirm error : {}", url);
                    break;
                } else {
                    success.add(node);
                }
            }
        }
        //虽然参与者会对超时情况进行处理，但我们尽量提供手工处理
        if (list.size() != success.size()) {
            success.forEach(node -> {
                InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(node.getServerName(), Boolean.FALSE);
                String url = instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + node.getServerUri();
                ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity(node.getData()), Response.class);
                if (!response.getBody().isSuccess()) {
                    //这里应该进行人工确认，最好进行入库操作
                    log.error("cancel error : {}", url);
                }
            });

            throw new BaseException(BusinessCode.ERROR_PARTICIPANT);
        }
    }


    //确认资源
    public void tccCancel(@RequestBody ParticipantRequest request) {
        List<Participant> list = request.getParticipants();
        List<Participant> success = request.getParticipants();
        if (!CollectionUtils.isEmpty(list)) {
            for (Participant node : list) {
                InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(node.getServerName(), Boolean.FALSE);
                String url = instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + node.getServerUri();
                ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity(node.getData()), Response.class);
                if (!response.getBody().isSuccess()) {
                    //这里按照white paper来说应该无所谓，应为没有确认操作
                    log.error("cancel error : {}", url);
                }
            }
        }

    }

}
