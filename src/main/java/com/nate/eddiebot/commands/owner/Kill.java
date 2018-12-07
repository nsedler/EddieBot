package com.nate.eddiebot.commands.owner;

import com.nate.eddiebot.util.bot.Categories;
import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;

/**
 * Shutdowns the bot
 *
 * @author Nate Sedler
 */
public class Kill extends Command {

    public Kill() {

        this.name = "kill";
        this.help = "Kills the bot";
        this.category = Categories.Owner;
        this.hidden = true;
        this.ownerOnly = true;
    }

    @Override
    protected void execute(BetterMessageEvent event) {
        event.reply("Shutting down...");
        event.getJDA().shutdownNow();
        System.exit(0);
	}
}
