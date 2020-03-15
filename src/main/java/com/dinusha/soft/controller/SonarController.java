package com.dinusha.soft.controller;

import com.dinusha.soft.model.SonarQube;
import com.dinusha.soft.service.SonarService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
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
    public @ResponseBody JSONObject getSonarViolationCount(@RequestBody Map<String, Object> map) {

        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject);


        return jsonObject;
//        try {
//            return String.valueOf(sonarService.getViolationCount(null));
//        } catch (ParseException | java.text.ParseException | IOException e) {
//            e.printStackTrace();
//        }
//        return "Sever error occurred contact administrator";
    }

}
