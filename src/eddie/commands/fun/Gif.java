package eddie.commands.fun;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.Categories;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Gif extends Command {

    public Gif(){

        this.name = "gif";
        this.arguments = "<query>";
        this.help = "Searches a gif on giphy.com";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(CommandEvent event) {

        String[] command = event.getMessage().getContentDisplay().split(" ", 2);

        try{

            command[1] = command[1].replace(" ", "+");

            String sURL = "https://api.giphy.com/v1/gifs/random?api_key=Bxx5K3s6bY2XymQ3zxsap4KDcNbDxLT6&tag=" + command[1]; //just a string

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

            event.replySuccess(test.get("url").getAsString());
        } catch (Exception e) {

            command[1] = command[1].replace("+", " ");
            event.reply("Could not find a search for " + command[1]);

        }
    }
}
