package com.example.tae.entity.ReleaseProcess.dto;

import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReleaseDto {
    private int id;
    private int release;

    public ReleaseProcess releaseProcess(int id, int release) {
        return ReleaseProcess.builder()
                .id(id)
                .releaseCNT(release)
                .build();
    }
}
