package com.bao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by nannan on 2017/8/24.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    private String serverName;//参与者服务名
    private String serverUri;//参与者服务地址
    private LocalDate expireTime; //参与者过期时间
    private String data; //参与者数据，json 格式
}
