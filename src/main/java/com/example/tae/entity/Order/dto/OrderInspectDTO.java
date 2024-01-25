package com.example.tae.entity.Order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInspectDTO {
    private Date inspectDate;
    private int planId;
}
