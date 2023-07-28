package com.isf1999.subway202307251023i.repository;

import com.isf1999.subway202307251023i.entity.LectureInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureInfoRepository extends JpaRepository<LectureInfo, Long> {
    LectureInfo findByNameAndLecturerAndCourseStartDate(String name, String lecturer, String CourseStartDate);
    List<LectureInfo> findByNameLike(String name);
}
