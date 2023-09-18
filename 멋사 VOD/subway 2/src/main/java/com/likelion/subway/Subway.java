package com.likelion.subway;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Subway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String useMonth; // 사용 월
    private String line; // 호선 정보
    private String name; // 역이름
    private double alight; // 오전 8시 ~ 9시 하차 인원

    @Builder
    public Subway(Long id, String useMonth, String line, String name, double alight) {
        this.id = id;
        this.useMonth = useMonth;
        this.line = line;
        this.name = name;
        this.alight = alight;
    }
}

