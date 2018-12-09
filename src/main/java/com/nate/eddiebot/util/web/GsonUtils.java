package com.nate.eddiebot.util.web;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * A GSON utitlies class
 * mainly to get JSON from web apis
 * 
 * @author Nate Sedler
 */
public class GsonUtils{

    private static RestWrapper rWrapper = new RestWrapper();
    private static JsonParser parser = new JsonParser();

    /**
     * Gets the JsonObject info from a website
     * 
     * @param url to json scrape
     * @return a JsonObject conatining all JSON from site
     */
    public static JsonObject getJsonObject(String url) {

        JsonElement jElement = parser.parse(rWrapper.getJSON(url));

        if(jElement.isJsonObject()){
            return jElement.getAsJsonObject();
        }

        return null;
    }
}