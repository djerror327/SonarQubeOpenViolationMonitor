package com.dinusha.soft.service;

/**
 * @author Dinusha Jayasekara on 3/15/2020
 * @project utility-monitor
 */

import com.dinusha.soft.client.SonarClient;
import com.dinusha.soft.model.SonarQube;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("unchecked")
@Service
@PropertySource("sonar.properties")
public class SonarService {

    @Autowired
    Environment env;

    public int getViolationCount(SonarQube sonarQube) throws ParseException, java.text.ParseException, IOException {

        String server = env.getProperty("sonar.server");
        String api = env.getProperty("sonar.api");
        String username = env.getProperty("sonar.username");
        String ps = env.getProperty("sonar.ps");

        String auth = username + ":" + ps;
        byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeaderValue = "Basic " + new String(encodeAuth);
        JSONObject jsonObject = SonarClient.getResponse(server + api + sonarQube.getProject(), authHeaderValue);
        JSONArray issuesList = (JSONArray) jsonObject.get("issues");

        ArrayList<String> list = new ArrayList<>();
        Date date = sonarQube.getDate();
        SimpleDateFormat dateSonar = new SimpleDateFormat("yyyy-MM");
        issuesList.forEach(jObj ->
                {
                    try {
                        if (((JSONObject) jObj).get("author").toString().equals(sonarQube.getAuthor()) && ((JSONObject) jObj).get("status").toString().equals("OPEN") && dateSonar.parse(String.valueOf(((JSONObject) jObj).get("updateDate")).substring(0, 7)).equals(date)) {
                            list.add(((JSONObject) jObj).get("status").toString());
                        }
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                }
        );
        return list.size();
    }
}
