package com.dinusha.soft.client;

/**
 * @author Dinusha Jayasekara on 3/15/2020
 * @project utility-monitor
 */

import com.dinusha.soft.utill.MethodType;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
class CommonClient {
    private CommonClient() {
    }

    static StringBuilder GETRequest(String URL, String authHeader) throws IOException {

        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authHeader);
        connection.setRequestMethod(MethodType.GET);

        String readLine;
        StringBuilder response = new StringBuilder();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((readLine = bufferedReader.readLine()) != null) {
                response.append(readLine);
            }
            bufferedReader.close();
        }
        return response;
    }
}
