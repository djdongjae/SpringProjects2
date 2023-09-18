package com.likelion.subway;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Subway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String USE_MON; // 사용 월
    private String LINE_NUM; // 호선 정보
    private String SUB_STA_NM; // 역이름
    private double EIGHT_ALIGHT_NUM; // 오전 8시 ~ 9시 하차 인원

    public Subway(Long id, String USE_MON, String LINE_NUM, String SUB_STA_NM, double EIGHT_ALIGHT_NUM) {
        this.id = id;
        this.USE_MON = USE_MON;
        this.LINE_NUM = LINE_NUM;
        this.SUB_STA_NM = SUB_STA_NM;
        this.EIGHT_ALIGHT_NUM = EIGHT_ALIGHT_NUM;
    }
}

