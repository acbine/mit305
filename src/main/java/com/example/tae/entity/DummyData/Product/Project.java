package com.example.tae.entity.DummyData.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * 제품 엔티티
 * 제품 이름 들어있음 ()
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Project {
    @Id
    String productName;
}
