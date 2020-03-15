package com.dinusha.soft.utill;

/**
 * @author Dinusha Jayasekara on 3/15/2020
 * @project utility-monitor
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class JSON {

    private JSON() {
    }

    private static JSONParser parser = new JSONParser();

    public static JSONObject parseJsonObject(StringBuilder value) throws ParseException {
        return (JSONObject) parser.parse(String.valueOf(value));
    }

    public static JSONArray parseJsonArray(StringBuilder value) throws ParseException {
        return (JSONArray) parser.parse(String.valueOf(value));
    }
}
