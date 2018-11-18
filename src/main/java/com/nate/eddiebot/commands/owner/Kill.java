package com.nate.eddiebot.commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;

public class Kill extends Command {

    public Kill() {

        this.name = "kill";
        this.help = "Kills the bot";
        this.category = Categories.Owner;
        this.hidden = true;
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("Bye Bye!");
        event.getJDA().shutdownNow();
        System.exit(0);
    }
}
