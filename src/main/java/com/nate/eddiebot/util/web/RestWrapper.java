package com.nate.eddiebot.util.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl; 
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 
 * A REST wrapper
 * mainly to get JSON from web apis
 * 
 * @author Nate Sedler
 */
public class RestWrapper {

    public static void main(String[] args) throws IOException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("message", "who are you?");

        System.out.println(getJSON("https://icanhazdadjoke.com/"));
    }
    
    private static OkHttpClient client = new OkHttpClient();

    /**
     * Gets whatever is shown on the webpage
     * 
     * @param url url you are scraping
     * @return scraped info
     */
    public static String getJSON(String url) {
     
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        Response response = null;

        String sURL = urlBuilder.build().toString();

        Request request = new Request.Builder()
            .url(sURL)
            .addHeader("User-Agent", "Mozilla/5.0")
            .addHeader("Accept", "application/json")
            .build();

        try{
            response = client.newCall(request).execute();
        
            return response.body().string();
        }catch(Exception e){
            e.printStackTrace();
        }
        return response.toString();
    }

    /**
     * Gets whatever is shown on the webpage
     * 
     * @param url url you are scraping
     * @param queryParameter sets the paramters of the request
     * @return scraped info
     */
    public static String getJSON(String url, Map<String, String> queryParameter) {
     
        Map.Entry<String,String> params = queryParameter.entrySet().iterator().next();
        Response response = null;

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder()
            .addQueryParameter(params.getKey(), params.getValue());

        String sURL = urlBuilder.build().toString();

        Request request = new Request.Builder()
            .url(sURL)
            
            .build();

        try{
            response = client.newCall(request).execute();
            return response.body().string();
        } catch(Exception e){
            e.printStackTrace();
        }
        return response.toString();
    }
}