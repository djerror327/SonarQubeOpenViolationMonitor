package com.dinusha.soft.client;

/**
 * @author Dinusha Jayasekara on 3/14/2020
 * @project utility-monitor
 */

import com.dinusha.soft.utill.JSON;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

@SuppressWarnings("unchecked")
public class SonarClient {
    public static JSONObject getResponse(String URL, String authHeader) throws IOException, ParseException, java.text.ParseException {

//        String auth = "admin:admin";
//        byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
//        String authHeaderValue = "Basic " + new String(encodeAuth);

//        URL url = new URL("http://localhost:9000/api/issues/search?projectKeys=Java_Robotic");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestProperty("Authorization", authHeaderValue);
//        connection.setRequestMethod("GET");
//
//        String readLine;
//        StringBuilder response = new StringBuilder();
//        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//            while ((readLine = bufferedReader.readLine()) != null) {
//                response.append(readLine);
//            }
//            bufferedReader.close();
//        }

        StringBuilder response = CommonClient.GETRequest(URL, authHeader);
        return JSON.parseJsonObject(response);


//        JSONParser parser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) parser.parse(String.valueOf(response));
//        JSONArray issuesList1 = (JSONArray) parser.parse(String.valueOf(response));
//        JSONArray issuesList = (JSONArray) jsonObject.get("issues");
//
//        ArrayList<String> list = new ArrayList<>();
//        Date date = new SimpleDateFormat("yyyy-MM").parse("2020-03-15");
//        SimpleDateFormat dateSonar = new SimpleDateFormat("yyyy-MM");
//        issuesList.forEach(jObj ->
//                {
//                    try {
//                        if (((JSONObject) jObj).get("author").toString().equals("djerror327@gmail.com") && ((JSONObject) jObj).get("status").toString().equals("OPEN") && dateSonar.parse(String.valueOf(((JSONObject) jObj).get("updateDate")).substring(0, 7)).equals(date)) {
//                            list.add(((JSONObject) jObj).get("status").toString());
//                        }
//                    } catch (java.text.ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//        );
//        System.out.println(list.size());


    }


//    public static void main(String[] args) {
//        try {
//            new SonarClient().getIssues();
//        } catch (IOException | ParseException | java.text.ParseException e) {
//            e.printStackTrace();
//        }
//    }
}
