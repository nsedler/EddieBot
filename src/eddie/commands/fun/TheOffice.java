package eddie.commands.fun;

import com.google.gson.*;
import eddie.helpful.JSONInfo;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

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

        if(message.equalsIgnoreCase(".theoffice")) try {

            // Connect to the URL using java's native library
            URL url = new URL("http://api.giphy.com/v1/gifs/random?tag=the+office&api_key=Bxx5K3s6bY2XymQ3zxsap4KDcNbDxLT6");
            URLConnection request = url.openConnection();
            request.setRequestProperty("User-Agent", "Mozilla/5.0");
            request.setRequestProperty("Accept", "application/json");
            request.connect();

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
//            System.out.println(rootobj);
            String text = rootobj.get("data.url").toString();

            System.out.println(text);

            channel.sendMessage(text).queue();


        } catch (JsonSyntaxException | IOException | NullPointerException e) {

            e.printStackTrace();
        }
    }
}
