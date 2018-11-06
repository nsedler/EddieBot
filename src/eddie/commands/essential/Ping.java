package eddie.commands.essential;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.Categories;

public class Ping extends Command {

    public Ping(){

        this.name = "ping";
        this.help = "Shows ping of EddieBot";
        this.category = Categories.Misc;
    }

    @Override
    protected void execute(CommandEvent event) {

        long currentTime = System.currentTimeMillis();
        event.reply("Pinging...", (message) -> message.editMessage("Pong! " + (System.currentTimeMillis() - currentTime) + "ms").queue());
    }
}
