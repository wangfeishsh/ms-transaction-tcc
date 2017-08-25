package com.bao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by nannan on 2017/8/25.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrozenDomain {
    private String tccId;
    private LocalDateTime expiration;
}
