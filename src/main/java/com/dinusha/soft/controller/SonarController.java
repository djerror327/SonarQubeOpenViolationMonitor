package com.dinusha.soft.controller;

import com.dinusha.soft.model.SonarQube;
import com.dinusha.soft.service.SonarService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dinusha Jayasekara on 3/15/2020
 * @project utility-monitor
 */

@Controller
public class SonarController {

    @Autowired
    SonarService sonarService;

    @RequestMapping(value = "/sonarViolationCount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody
    Object getSonarViolationCount(@RequestBody Map<String, Object> map) {


        try {
            JSONObject jsonObject = new JSONObject(map);
            SonarQube sonarQube = new SonarQube();
            sonarQube.setDate((String) jsonObject.get("date"));
            sonarQube.setProject((String) jsonObject.get("project"));
            sonarQube.setAuthor((String) jsonObject.get("author"));

            int vCount = sonarService.getViolationCount(sonarQube);
            HashMap<String, Integer> hashMap = new HashMap<>();
            hashMap.put("vcount", vCount);
            return hashMap;

        } catch (ParseException | java.text.ParseException | IOException e) {
            e.printStackTrace();
        }
        HashMap<String, String> mapError = new HashMap<>();
        mapError.put("vcounterror", "Sever error occurred while getting violation count contact administrator!");
        return mapError;
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<String> getProjects() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Data not loaded");
        try {
            return sonarService.getProjects();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
