package eddie.commands.fun;

import com.google.gson.*;
import eddie.helpful.JSONInfo;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TheOffice extends ListenerAdapter {

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        //http://api.giphy.com/v1/gifs/random?tag=the+office&api_key=Bxx5K3s6bY2XymQ3zxsap4KDcNbDxLT6

        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();

        if (message.equalsIgnoreCase(".theoffice")) try {

            String sURL = "https://api.giphy.com/v1/gifs/random?api_key=Bxx5K3s6bY2XymQ3zxsap4KDcNbDxLT6&tag=theoffice"; //just a string

            // Connect to the URL using java's native library
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.setRequestProperty("User-Agent", "Mozilla/5.0");
            request.setRequestProperty("Accept", "application/json");
            request.connect();

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootobj = root.getAsJsonObject();
            JsonObject test = rootobj.get("data").getAsJsonObject();

            channel.sendMessage(test.get("url").getAsString()).queue();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
