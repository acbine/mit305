package com.example.tae.entity.Order.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class OrderDTO {
    private String departName;
    private String productName;
    private int productCode;
    private int num;
    private int LT;
    private Date orderDate;
    private String tel;
    private String fax;
    private Date projectOutPutDate;
    private String agent;
    private String email;
}
