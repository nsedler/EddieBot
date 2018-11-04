package eddie.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.JSONInfo;
import net.dv8tion.jda.core.entities.MessageChannel;

public class Joke extends Command {

    public Joke(Category c){

        this.name = "joke";
        this.help = "Gives a random dad joke";
        this.category = c;
    }

    @Override
    protected void execute(CommandEvent event) {

        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();

        if(message.equalsIgnoreCase(".joke")) {

            JSONInfo json = new JSONInfo();

            channel.sendMessage(json.JSONText("https://icanhazdadjoke.com/", "joke")).queue();
        }
    }
}


