package com.isf1999.subway202307251023i.controller;

import com.isf1999.subway202307251023i.entity.LectureInfo;
import com.isf1999.subway202307251023i.entity.SubstationInfo;
import com.isf1999.subway202307251023i.repository.LectureInfoRepository;
import com.isf1999.subway202307251023i.repository.SubstationInfoRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.util.List;

@Controller
public class HomeController {
    @Autowired private SubstationInfoRepository substationInfoRepository;

    @Autowired private LectureInfoRepository lectureInfoRepository;

    @GetMapping("/api")
    public String index() {
        return "index";
    }

    @PostMapping("/api")
    public @ResponseBody List<SubstationInfo> load_save(@RequestParam("date") String date, Model model) {
        String result = "";

        try {
            String requestDate = date;
            URL url = new URL(
                    "http://openapi.seoul.go.kr:8088/"
                            + "4d5554784769736638367049586972/"
                            + "json/CardSubwayStatsNew/1/700/"
                            +requestDate
            );

            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject CardSubwayStatsNew = (JSONObject) jsonObject.get("CardSubwayStatsNew");
            Long totalCount = (Long) CardSubwayStatsNew.get("list_total_count");

            JSONObject subResult = (JSONObject) CardSubwayStatsNew.get("RESULT");
            JSONArray infoArr = (JSONArray) CardSubwayStatsNew.get("row");

            for (int i = 0; i < infoArr.size(); i++) {
                JSONObject tmp = (JSONObject) infoArr.get(i);
                SubstationInfo infoObj = new SubstationInfo(i + (long)1,
                        (String)tmp.get("USE_DT"),
                        (String)tmp.get("LINE_NUM"),
                        (String)tmp.get("SUB_STA_NM"),
                        (double)tmp.get("RIDE_PASGR_NUM"),
                        (double)tmp.get("ALIGHT_PASGR_NUM"),
                        (String)tmp.get("WORK_DT"));
                substationInfoRepository.save(infoObj);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return substationInfoRepository.findAll();

    }

    @GetMapping("list")
    public String lecture(Model model) {
        model.addAttribute("lectures", lectureInfoRepository.findAll());
        return "list";
    }

    @PostMapping("list")
    public String lecture(Model model, String srchText) {
        if (srchText == null) srchText = "";
        model.addAttribute("lectures", lectureInfoRepository.findByNameLike("%" + srchText + "%"));
        return "list";
    }

    @RequestMapping("load_save")
    public String load_save() throws Exception {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("/Users/dongjae/Desktop/SpringProjects2/멋사 VOD/subway202307251023i/src/main/resources/static/lecture.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray records = (JSONArray) jsonObject.get("records");

        for (int i = 0; i < records.size(); i++) {
            JSONObject tmp = (JSONObject) records.get(i);
            LectureInfo lectureInfo = new LectureInfo(
                    i + (long)1,
                    (String) tmp.get("강좌명"),
                    (String) tmp.get("강사명"),
                    (String) tmp.get("교육시작일자"),
                    (String) tmp.get("교육종료일자"),
                    (String) tmp.get("강좌내용"),
                    (String) tmp.get("교육대상구분"),
                    (String) tmp.get("교육방법구분"),
                    (String) tmp.get("운영요일"),
                    (String) tmp.get("교육장소"),
                    (String) tmp.get("강좌정원수"),
                    (String) tmp.get("수강료"),
                    (String) tmp.get("교육장도로명주소"),
                    (String) tmp.get("운영기관명")
            );
            lectureInfoRepository.save(lectureInfo);
        }

        return "success";

    }
}
