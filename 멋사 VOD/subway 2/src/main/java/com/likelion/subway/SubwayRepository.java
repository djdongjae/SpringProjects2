package com.likelion.subway;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubwayRepository extends JpaRepository<Subway, Long> {

    List<Subway> findByUseMonth(String month);

    List<Subway> findByUseMonthOrderByAlightDesc(String month);

}
