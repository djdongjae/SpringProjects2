package com.isf1999.subway_2023_07_25_0922i.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SubstationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String USE_DT;

    @Column
    private String LINE_NUM;

    @Column
    private String SUB_STA_NM;

    @Column
    private double RIDE_PASGR_NUM;

    @Column
    private double ALIGHT_PASGR_NUM;

    @Column
    private String WORK_DT;

    public SubstationInfo(Long id, String USE_DT, String LINE_NUM, String SUB_STA_NM, double RIDE_PASGR_NUM, double ALIGHT_PASGR_NUM, String WORK_DT) {
        this.id = id;
        this.USE_DT = USE_DT;
        this.LINE_NUM = LINE_NUM;
        this.SUB_STA_NM = SUB_STA_NM;
        this.RIDE_PASGR_NUM = RIDE_PASGR_NUM;
        this.ALIGHT_PASGR_NUM = ALIGHT_PASGR_NUM;
        this.WORK_DT = WORK_DT;
    }

    public SubstationInfo() {

    }
}
