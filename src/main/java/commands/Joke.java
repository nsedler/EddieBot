package main.java.commands;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Joke extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event){


        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();

        if(message.equalsIgnoreCase(".joke")) {

            try {

                String sURL = "https://icanhazdadjoke.com/"; //just a string

                // Connect to the URL using java's native library
                URL url = new URL(sURL);
                URLConnection request = url.openConnection();
                request.setRequestProperty("User-Agent", "Mozilla/5.0");
                request.setRequestProperty("Accept", "application/json");
                request.connect();


                // Convert to a JSON object to print data
                JsonParser jp = new JsonParser(); //from gson
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
                JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
                String joke = rootobj.get("joke").getAsString(); //just grab the zipcode

                channel.sendMessage(joke).queue();
            } catch (JsonSyntaxException | IOException e) {

                e.printStackTrace();
            }
        }
    }
}


