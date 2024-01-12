package com.example.tae.entity.DummyData.Classification;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Assy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assyId", nullable = false)
    private Long id;

    String assy;

    @ManyToOne
    @JoinColumn(name = "unitId")
    private Unit unit;
}
