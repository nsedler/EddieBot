package eddie.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.JSONInfo;
import net.dv8tion.jda.core.entities.MessageChannel;

public class ChuckNorris extends Command {

    public ChuckNorris(Category c) {

        this.name = "chuck";
        this.help = "Chuck norris Jokes";
        this.category = c;
    }

    @Override
    protected void execute(CommandEvent event) {

        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();

        if (message.equalsIgnoreCase(".chuck")) {

            JSONInfo json = new JSONInfo();

            channel.sendMessage(json.JSONText("https://api.chucknorris.io/jokes/random", "value")).queue();
        }
    }
}
