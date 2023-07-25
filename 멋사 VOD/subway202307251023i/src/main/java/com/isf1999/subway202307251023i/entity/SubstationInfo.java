package com.isf1999.subway202307251023i.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "SUBSTATION_INFO")
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

    @Builder
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
