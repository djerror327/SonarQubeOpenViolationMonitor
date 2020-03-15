package com.dinusha.soft.utill;

/**
 * @author Dinusha Jayasekara on 3/15/2020
 * @project utility-monitor
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON {

    private JSON() {
    }

    private static JSONParser parser = new JSONParser();

    public static JSONObject parseJsonObject(StringBuilder response) throws ParseException {
        return (JSONObject) parser.parse(String.valueOf(response));
    }

    public static JSONArray parseJsonArray(StringBuilder response) throws ParseException {
        return (JSONArray) parser.parse(String.valueOf(response));
    }
}