package com.bao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by nannan on 2017/8/25.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrozenRequest {
    private String orderId;
    private String userId;
    private String money;
}
