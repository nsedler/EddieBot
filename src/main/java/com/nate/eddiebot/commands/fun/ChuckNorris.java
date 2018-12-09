package com.nate.eddiebot.commands.fun;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.Categories;
import com.nate.eddiebot.util.bot.DefaultEmbed;
import com.nate.eddiebot.util.web.GsonUtils;

import net.dv8tion.jda.core.EmbedBuilder;

/**
 * Facts about chuck norris
 * 
 * @author Nate Sedler
 */
public class ChuckNorris extends Command {

    public ChuckNorris() {

        this.name = "chuck";
        this.help = "Facts about Chuck Norris";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        String sURL = "https://api.chucknorris.io/jokes/random";

        EmbedBuilder x = DefaultEmbed.embedDefault();

        x.appendDescription(GsonUtils.getJsonObject(sURL).get("value").getAsString());

        event.reply(x.build());
	}
}
