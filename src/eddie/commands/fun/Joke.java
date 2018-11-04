package eddie.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.JSONInfo;

public class Joke extends Command {

    public Joke(Category c) {

        this.name = "joke";
        this.help = "Gives a random dad joke";
        this.category = c;
    }

    @Override
    protected void execute(CommandEvent event) {

        JSONInfo json = new JSONInfo();

        event.reply(json.JSONText("https://icanhazdadjoke.com/", "joke"));
    }
}


