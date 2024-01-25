package com.example.tae.entity.ProcurementPlan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcurementPlanJoinDTO {

    private String productName;
    private int productCode;
    private int outPutNum;
    private int productCodeCount;
    private int leadTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT") // 날짜 명시
    private Date projectOutputDate;
    private String departmentName;

    private int projectPlanCode;
    private int contractCode;
    private String projectName;

}
