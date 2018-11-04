package eddie.commands.fun;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Woof extends Command {

    public Woof(Category c){

        this.name = "woof";
        this.help = "Gives random pictures/videos of dogs";
        this.category = c;
    }

    @Override
    protected void execute(CommandEvent event) {

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

                if (file.exists()) {

                    file.delete();
                    Files.delete(path);
                }

                if (doge.contains(".mp4")) {

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\eddie\\commands\\essential\\doge.mp4");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                } else if (doge.contains(".gif")) {

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\eddie\\commands\\essential\\doge.gif");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                } else if (doge.contains(".jpg") || doge.contains(".JPG")) {

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\eddie\\commands\\essential\\doge.jpg");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                } else if (doge.contains(".png")) {

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\eddie\\commands\\essential\\doge.png");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                } else if (doge.contains(".webm")) {

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\eddie\\commands\\essential\\doge.webm");
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
}
