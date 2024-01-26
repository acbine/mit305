package com.example.tae.entity.Order.dto;

import com.example.tae.entity.Contract.Contract;
import com.example.tae.entity.Order.Purchase;
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
    private int procurementPlanCode;
    private int productCode;
    private int num;
    private int LT;
    private int contractCode;
    private Date orderDate;
    private String tel;
    private String fax;
    private Date projectOutPutDate;
    private String agent;
    private String email;
    private int existence;
    private int length;
    private int width;
    private int height;
    private String text;
    private String orderState;
    private boolean progressInspectionStatus;

    public void setProgressInspectionStatus() {
        switch (this.orderState) {
            case "검수처리완료", "발주전", "마감"  -> this.progressInspectionStatus = true;
            case "발주중" -> this.progressInspectionStatus = false;
        }
    }
}
