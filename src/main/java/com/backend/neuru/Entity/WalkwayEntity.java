package com.backend.neuru.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="walkway")
public class WalkwayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String walkway_description;
    private int keyword; // 0:혼자, 1:반려견, 2:동반자
    private int like_count;
    private Long cityID;

    @OneToOne
    @JoinColumn(name = "walkwayJSON_id", nullable = false)
    private WalkwayJSONEntity walkwayJSON;

}
