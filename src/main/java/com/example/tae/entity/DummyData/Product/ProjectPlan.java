package com.example.tae.entity.DummyData.Product;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * 제품 생산계획에 관한 엔티티 입니다
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ProjectPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int outPuteNum;

    @Temporal(TemporalType.DATE)
    private Date projectOutputDate;

    @ManyToOne
    Project product;
}
