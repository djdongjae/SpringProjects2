package com.isf1999.subway_2023_07_25_0922i.controller;

import com.isf1999.subway_2023_07_25_0922i.entity.SubstationInfo;
import com.isf1999.subway_2023_07_25_0922i.repository.SubstationInfoRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class HomeController {
    @Autowired
    private SubstationInfoRepository infoRepository;

    @GetMapping("/api")
    public String index() {
        return "index";
    }

    @PostMapping("/api")
    public String load_save(@RequestParam("date") String date, Model model) {
        String result = "";

        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
            urlBuilder.append("/" +  URLEncoder.encode("sample","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
            urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
            urlBuilder.append("/" + URLEncoder.encode("CardSubwayStatsNew","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
            urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
            urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
            // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

            // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
            urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/

            URL url = new URL(urlBuilder.toString());

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
                System.out.println(infoObj);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "redirect:/findname";
    }
}
