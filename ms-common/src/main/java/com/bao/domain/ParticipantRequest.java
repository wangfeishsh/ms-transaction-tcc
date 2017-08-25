package com.bao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by nannan on 2017/8/24.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantRequest {
    private String tccId;
    private List<Participant> participants;
}
