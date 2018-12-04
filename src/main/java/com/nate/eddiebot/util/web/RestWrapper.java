package com.nate.eddiebot.util.web;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * //TODO add a REST wrapper
 * 
 * A REST wrapper
 * mainly to get JSON from web apis
 * 
 * @author Nate Sedler
 */
public class RestWrapper {

    public static void main(String[] args) throws IOException {

        Map<String, String> params = new LinkedHashMap();
        params.put("message", "who are you?");

        System.out.println(getJSON("https://some-random-api.ml/chatbot/", params));
    }
    
    private static OkHttpClient client = new OkHttpClient();

    /**
     * Gets whatever is shown on the webpage
     * 
     * @param url url you are scraping
     * @return scraped info
     */
    private static String getJSON(String url) throws IOException {
     
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();

        String sURL = urlBuilder.build().toString();

        Request request = new Request.Builder()
                     .url(sURL)
                     .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * Gets whatever is shown on the webpage
     * 
     * @param url url you are scraping
     * @param queryParameter sets the paramters of the request
     * @return scraped info
     */
    private static String getJSON(String url, Map<String, String> queryParameter) throws IOException {
     
        Map.Entry<String,String> entry = queryParameter.entrySet().iterator().next();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder()
            .addQueryParameter(entry.getKey(), entry.getValue());

        String sURL = urlBuilder.build().toString();

        Request request = new Request.Builder()
                     .url(sURL)
                     .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}