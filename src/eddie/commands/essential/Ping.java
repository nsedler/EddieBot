package eddie.commands.essential;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Ping extends Command {

    public Ping(Category c){

        this.name = "ping";
        this.help = "Shows ping of EddieBot";
        this.category = c;
    }

    @Override
    protected void execute(CommandEvent event) {

        long currentTime = System.currentTimeMillis();
        event.reply("Pinging...", (message) -> message.editMessage("Pong! " + (System.currentTimeMillis() - currentTime) + "ms").queue());
    }
}
