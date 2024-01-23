package com.example.tae.entity.ProcurementPlan.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
public class ProcurementPlanJoinDTO {

    String product_name;
    int product_code;
    int out_pute_num;
    int product_code_count;
    int lead_time;
    Date project_output_date;
    String depart_name;
//    int id;
//    int contract_code;
}
