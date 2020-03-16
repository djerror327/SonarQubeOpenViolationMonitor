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
import java.util.*;

@SuppressWarnings("unchecked")
@Service
@PropertySource("sonar.properties")
public class SonarService {

    @Autowired
    Environment env;

    private String getAuthHeader() {
        String username = env.getProperty("sonar.username");
        String ps = env.getProperty("sonar.ps");

        String auth = username + ":" + ps;
        byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        return ("Basic " + new String(encodeAuth));
    }

    public int getViolationCount(SonarQube sonarQube) throws ParseException, IOException {

        String server = env.getProperty("sonar.server");
        String api = env.getProperty("sonar.api.issues");
        String pageSize = env.getProperty("sonar.api.ps");

        JSONObject jsonObject = SonarClient.getResponse(server + api + sonarQube.getProject() + pageSize, getAuthHeader());
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

    public List<String> getProjects() throws IOException, ParseException {
        String server = env.getProperty("sonar.server");
        String api = env.getProperty("sonar.api.projects");
        JSONObject jsonObject = SonarClient.getResponse(server + api, getAuthHeader());
        JSONArray jsonArray = (JSONArray) jsonObject.get("components");
        ArrayList<String> list = new ArrayList<>();
        jsonArray.forEach(jObj ->
                list.add(((JSONObject) jObj).get("key").toString())
        );
        Collections.sort(list);
        return list;
    }

    public List<String> getAuthors(SonarQube sonarQube) throws IOException, ParseException {
        String server = env.getProperty("sonar.server");
        String api = env.getProperty("sonar.api.issues");
        String pageSize = env.getProperty("sonar.api.ps");

        JSONObject jsonObject = SonarClient.getResponse(server + api + sonarQube.getProject() + pageSize, getAuthHeader());
        JSONArray issuesList = (JSONArray) jsonObject.get("issues");
        ArrayList<String> list = new ArrayList<>();
        issuesList.forEach(jObj ->
                list.add(((JSONObject) jObj).get("author").toString())
        );
        HashSet<String> hashSet = new HashSet<>(list);
        ArrayList<String> listAuthors = new ArrayList<>(hashSet);
        Collections.sort(listAuthors);
        return listAuthors;
    }
}
