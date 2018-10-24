package main.java.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Woof extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {


        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();

        if (message.equalsIgnoreCase(".woof")) {

            try {

                String sURL = "https://random.dog/woof.json"; //just a string

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
                String doge = rootobj.get("url").getAsString(); //just grab the zipcode

                InputStream in = new URL(doge).openStream();

                Path path = null;
                File file = new File(String.valueOf(path));

                if(file.exists()){

                    file.delete();
                    Files.delete(path);
                }
                
                if(doge.contains(".mp4")) {

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\main\\java\\commands\\doge.mp4");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                } else if(doge.contains(".gif")){

                    restart();
                    request.connect();
                } else if(doge.contains(".jpg") || doge.contains(".JPG")){

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\main\\java\\commands\\doge.jpg");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                } else if(doge.contains(".png")){

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\main\\java\\commands\\doge.png");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                } else if(doge.contains(".webm")){

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\main\\java\\commands\\doge.webm");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                }

                channel.sendFile(file).complete();

                Files.delete(path);
                file.delete();
            } catch (JsonSyntaxException | IOException e) {

                e.printStackTrace();
            }
        }
    }

    private void restart(){

        try {
            String sURL = "https://random.dog/woof.json"; //just a string

            // Connect to the URL using java's native library
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.setRequestProperty("User-Agent", "Mozilla/5.0");
            request.setRequestProperty("Accept", "application/json");
            request.connect();
            request.connect();
        } catch (JsonSyntaxException | IOException e) {

            e.printStackTrace();
        }
    }
}
