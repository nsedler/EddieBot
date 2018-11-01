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

            URL loginurl = new URL("https://api.giphy.com/v1/gifs/random?tag=the+office&api_key=Bxx5K3s6bY2XymQ3zxsap4KDcNbDxLT6");
            URLConnection yc = loginurl.openConnection();
            yc.setRequestProperty("Accept", "application/json");
            yc.setConnectTimeout(10 * 1000);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine = in.readLine();
            JsonParser parser = new JsonParser();
            JsonObject array = parser.parse(inputLine).getAsJsonObject();
            System.out.println(array);
            String gif = array.get("data.url").toString();

            channel.sendMessage("hi").queue();
            channel.sendMessage(gif).queue();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
