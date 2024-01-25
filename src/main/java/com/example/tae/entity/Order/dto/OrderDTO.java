package com.example.tae.entity.Order.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class OrderDTO {
    private String departName;          // 업체명
    private LocalDateTime dateOfOrder;  // 발주서 발행일
    private String businessNumber;      // 사업자번호
    private String businessName;        // 대표자
    private String businessEmail;       // 회사 이메일

    private String productName;         // 품목명
    private int productCode;            // 품목코드
    private int outputNum;              // 필요수량
    private int LT;                     // L/T
    private Date orderDate;             // 발주일
    private int width;                  // 규격(가로)
    private int length;                  // 규격(세로)
    private int height;                  // 규격(높이)
    private int weight;                 // 중량
    private String texture;             // 재질

}