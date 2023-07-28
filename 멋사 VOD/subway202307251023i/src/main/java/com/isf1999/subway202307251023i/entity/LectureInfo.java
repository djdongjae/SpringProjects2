package com.isf1999.subway202307251023i.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "LECTURE_INFO")
@Data
public class LectureInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lecturer;

    @Column
    private String courseStartDate;

    @Column
    private String courseEndDate;

    @Column(length = 1000)
    private String content;

    @Column
    private String educationTarget;

    @Column
    private String onOff;

    @Column
    private String day;

    @Column
    private String location;

    @Column
    private String capacity;

    @Column
    private String cost;

    @Column
    private String lectureAddress;

    @Column
    private String organization;

    @Builder
    public LectureInfo(Long id, String name, String lecturer, String courseStartDate, String courseEndDate, String content, String educationTarget, String onOff, String day, String location, String capacity, String cost, String lectureAddress, String organization) {
        this.id = id;
        this.name = name;
        this.lecturer = lecturer;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.content = content;
        this.educationTarget = educationTarget;
        this.onOff = onOff;
        this.day = day;
        this.location = location;
        this.capacity = capacity;
        this.cost = cost;
        this.lectureAddress = lectureAddress;
        this.organization = organization;
    }

    public LectureInfo() {
    }

}
