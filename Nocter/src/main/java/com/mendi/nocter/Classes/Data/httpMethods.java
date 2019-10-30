/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mendi.nocter.Classes.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import static javafx.css.StyleOrigin.USER_AGENT;
import com.mendi.nocter.Classes.message;
import java.io.OutputStream;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser;

/**
 *
 * @author manuel
 */
public class httpMethods {
    public static String httpUrl = "http://programoyformateo.com/Nocter/public/index.php/api/";
    public static String key = "^STp2P$gturU4QPtG2HnTYzxLbwfMQ$H*tq@K8e5!vLSHG!mtJZUTv4rbeKjabu@FYXKnV7PzWh@fKea9Kdb5EQPjr";
    public static JSONArray Send(String method, String endPoint, String params, String jsonInputString){
        JSONArray jsonResponse = new JSONArray();
        try{
            params = (params.length()>0? "?" + params + "&": "?");
            URL url = new URL(httpUrl+ endPoint + params + "key=" + key);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //Check the connection type
            con.setRequestMethod(method.toUpperCase());
            switch(method.toUpperCase()){
                case "GET":
                    int responseCode = con.getResponseCode();
                    /*BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                    }
                    in.close();
                    jsonResponse = parseJson(response.toString().replace("[", "").replace("]", ""));*/
                    break;
                case "POST":
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestProperty("Content-Type", "application/json; utf-8");
                    try(OutputStream os = con.getOutputStream()) {
                        byte[] input = jsonInputString.getBytes("utf-8");
                        os.write(input, 0, input.length);           
                    }
                    /*try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                          StringBuilder responseb = new StringBuilder();
                          String responseLine = null;
                          while ((responseLine = br.readLine()) != null) {
                              responseb.append(responseLine.trim());
                          }
                          jsonResponse = parseJson(responseb.toString().replace("[", "").replace("]", ""));
                      }*/
                    break;
                case "PUT":
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestProperty("Content-Type", "application/json; utf-8");
                    try(OutputStream os = con.getOutputStream()) {
                        byte[] input = jsonInputString.getBytes("utf-8");
                        os.write(input, 0, input.length);           
                    }
                    break;
                case "DELETE":
                    con.connect();
                    break;
                default:
                    return parseJson(getJSONformat("Error","Method not valid"));
            }
             BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                    }
                    in.close();
                    jsonResponse = parseJson(response.toString());//.replace("[", "").replace("]", ""));
            return jsonResponse;
        }
        catch(Exception ex){
            return parseJson(getJSONformat("Error",ex.getMessage()));
        }
    }
    public static JSONArray parseJson(String parse){
        try{
            JSONParser parser = new JSONParser();
            //if the api not return the correct array format.
            parse = (parse.contains("["))?parse:"[" + parse + "]";
            return (JSONArray) parser.parse(parse);
        }catch(Exception ex){
            return null;
        }        
    }
    public static String getJSONformat(String name, String value){
        return "[{\"" + name + "\":\"" + value + "\"]}";
    }
}
