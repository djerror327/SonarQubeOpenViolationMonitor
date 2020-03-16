package com.dinusha.soft.client;

/**
 * @author Dinusha Jayasekara on 3/14/2020
 * @project utility-monitor
 */

import com.dinusha.soft.utill.JSON;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;

        @SuppressWarnings("unchecked")
                @Component
                public class SonarClient {

                    public static JSONObject getResponse(String URL, String authHeader) throws IOException, ParseException {
                        StringBuilder response = CommonClient.GETRequest(URL, authHeader);
                        return JSON.parseJsonObject(response);
                    }

                    public static Boolean getServerStatus(String server) throws IOException {
                        return CommonClient.checkServerStatus(server);
    }
}
