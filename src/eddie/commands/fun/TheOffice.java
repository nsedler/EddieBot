package eddie.commands.fun;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.ListedEmote;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.Route;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TheOffice extends Command {

    public TheOffice(Category c) {

        this.name = "theoffice";
        this.help = "Gives a random gif from The Office";
        this.category = c;
    }

    @Override
    protected void execute(CommandEvent event) {

        try{
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

            Emote e = event.getJDA().getEmoteById("508750047102369811");

            event.reply(test.get("url").getAsString(), (message) -> message.addReaction(e).queue());

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}

