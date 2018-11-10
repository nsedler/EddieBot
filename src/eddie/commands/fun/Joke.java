package eddie.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.Categories;
import eddie.helpful.JSONInfo;

public class Joke extends Command {

    public Joke() {

        this.name = "joke";
        this.help = "Gives a random dad joke";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(CommandEvent event) {

        JSONInfo json = new JSONInfo();

        event.reply(json.jsonText("https://icanhazdadjoke.com/", "joke"));
    }
}


