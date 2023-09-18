package com.likelion.subway;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class SubwayController {

    private final SubwayRepository subwayRepository;

    @GetMapping("/")
    public String searchMonth(Model model) {
        return "index";
    }

    @PostMapping("/searchMonth")
    public String searchMonth(@RequestParam("month") String month, Model model) {
        String result = "";

        try {
            String requestMonth = month;
            URL url = new URL("http://openapi.seoul.go.kr:8088/" +
                    "4a4c4e654669736636376e7172776d" +
                    "/json/CardSubwayTime/1/1000/" +
                    requestMonth);

            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject CardSubwayStatsNew = (JSONObject)jsonObject.get("CardSubwayTime");
            JSONArray infoArr = (JSONArray) CardSubwayStatsNew.get("row");

            if (subwayRepository.findByUseMonth(requestMonth).size() == 0) {
                for (int i = 0; i < infoArr.size(); i++) {
                    JSONObject tmp = (JSONObject)infoArr.get(i);

                    Subway infoObj = Subway.builder()
                            .id(i + (long)1)
                            .useMonth((String)tmp.get("USE_MON"))
                            .line((String)tmp.get("LINE_NUM"))
                            .name((String)tmp.get("SUB_STA_NM"))
                            .alight((double)tmp.get("EIGHT_ALIGHT_NUM"))
                            .build();

                    subwayRepository.save(infoObj);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/find?month=" + month;
    }

    @GetMapping("/find")
    public String find(@RequestParam("month") String month, Model model) {
        List<Subway> subways = subwayRepository.findByUseMonthOrderByAlightDesc(month);
        model.addAttribute("subways", subways);
        return "result";
    }

}
