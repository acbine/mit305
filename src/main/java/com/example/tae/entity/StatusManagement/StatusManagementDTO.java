package com.example.tae.entity.StatusManagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StatusManagementDTO {
    String orderStateForSMString;
    Long count;
}
