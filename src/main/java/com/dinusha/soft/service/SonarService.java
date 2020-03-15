package com.dinusha.soft.service;

/**
 * @author Dinusha Jayasekara on 3/15/2020
 * @project utility-monitor
 */

import com.dinusha.soft.client.SonarClient;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("unchecked")
public class SonarService {

    static int getViolationCount() throws ParseException, java.text.ParseException, IOException {
        String auth = "admin:admin";
        byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeaderValue = "Basic " + new String(encodeAuth);
        JSONObject jsonObject = SonarClient.getResponse("http://localhost:9000/api/issues/search?projectKeys=Java_Robotic", authHeaderValue);
        JSONArray issuesList = (JSONArray) jsonObject.get("issues");

        ArrayList<String> list = new ArrayList<>();
        Date date = new SimpleDateFormat("yyyy-MM").parse("2020-03-15");
        SimpleDateFormat dateSonar = new SimpleDateFormat("yyyy-MM");
        issuesList.forEach(jObj ->
                {
                    try {
                        if (((JSONObject) jObj).get("author").toString().equals("djerror327@gmail.com") && ((JSONObject) jObj).get("status").toString().equals("OPEN") && dateSonar.parse(String.valueOf(((JSONObject) jObj).get("updateDate")).substring(0, 7)).equals(date)) {
                            list.add(((JSONObject) jObj).get("status").toString());
                        }
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                }
        );
        return list.size();
    }

    public static void main(String[] args) {
        try {
            System.out.println(SonarService.getViolationCount());
        } catch (ParseException | java.text.ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
