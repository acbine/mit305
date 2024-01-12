package com.example.tae.entity.DummyData.Classification;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String part;

    @ManyToOne
    @JoinColumn(name = "assyId")
    private Assy assy;

}
