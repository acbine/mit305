package com.example.tae.entity.DummyData.Product;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectPlanDTO {
    int projectCode;

    String projectName;

    Integer setouputNum;

    Date setProjectPlanDate;
}
