package com.backend.neuru.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="my_walkway")
public class MyWalkwayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "walkway_id", nullable = false)
    private WalkwayEntity walkway;
}
