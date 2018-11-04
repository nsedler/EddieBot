package eddie.commands.fun;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Meow extends Command {

    public Meow(Category c){

        this.name = "meow";
        this.help = "Gives a random pictures/videos of cats";
        this.category = c;
    }

    @Override
    protected void execute(CommandEvent event) {


        TextChannel channel = event.getTextChannel();

            try {

                String sURL = "https://aws.random.cat/meow"; //just a string

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
                String cat = rootobj.get("file").getAsString(); //just grab the zipcode

                InputStream in = new URL(cat).openStream();

                Path path = null;
                File file = new File(String.valueOf(path));

                if (file.exists()) {

                    file.delete();
                    Files.delete(path);
                }

                if (cat.contains(".mp4")) {

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\eddie\\commands\\essential\\cat.mp4");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                } else if (cat.contains(".gif")) {

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\eddie\\commands\\essential\\cat.gif");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                } else if (cat.contains(".jpg") || cat.contains(".JPG") || cat.contains(".jpeg")) {

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\eddie\\commands\\essential\\cat.jpg");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                } else if (cat.contains(".png")) {

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\eddie\\commands\\essential\\cat.png");
                    file = new File(String.valueOf(path));
                    Files.copy(in, path);
                } else if (cat.contains(".webm")) {

                    path = Paths.get("C:\\Users\\Nate Sedler\\Documents\\EddieBot\\src\\eddie\\commands\\essential\\cat.webm");
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

