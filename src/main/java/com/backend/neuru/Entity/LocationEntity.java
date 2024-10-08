package com.backend.neuru.Entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="location")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String COT_COORD_X;
    private String COT_COORD_Y;
    private int category; // 0:화장실, 1:충전기, 2:영화관
    private Boolean is_ad;

}
