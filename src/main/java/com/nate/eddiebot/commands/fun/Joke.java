package com.nate.eddiebot.commands.fun;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.Categories;
import com.nate.eddiebot.util.bot.DefaultEmbed;
import com.nate.eddiebot.util.web.GsonUtils;

import net.dv8tion.jda.core.EmbedBuilder;

/**
 * Gets a random dad joke
 * 
 * @author Nate Sedler
 */
public class Joke extends Command {

    public Joke() {

        this.name = "joke";
        this.help = "Gives a random dad joke";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        String sURL = "https://icanhazdadjoke.com/";

        EmbedBuilder x = DefaultEmbed.embedDefault();

        x.setDescription(GsonUtils.getJsonObject(sURL).get("joke").getAsString());

        event.reply(x.build());
	}
}


