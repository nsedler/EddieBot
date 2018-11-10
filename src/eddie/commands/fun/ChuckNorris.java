package eddie.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.Categories;
import eddie.helpful.JSONInfo;
import net.dv8tion.jda.core.entities.MessageChannel;

public class ChuckNorris extends Command {

    public ChuckNorris() {

        this.name = "chuck";
        this.help = "Chuck norris Jokes";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(CommandEvent event) {

        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();

        if (message.equalsIgnoreCase(".chuck")) {

            JSONInfo json = new JSONInfo();

            channel.sendMessage(json.jsonText("https://api.chucknorris.io/jokes/random", "value")).queue();
        }
    }
}
