package com.nate.eddiebot.commands.fun;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.Categories;
import com.nate.eddiebot.util.bot.DefaultEmbed;
import com.nate.eddiebot.util.web.GsonUtils;
import net.dv8tion.jda.core.EmbedBuilder;

/**
 * Adorable cats
 *
 * @author Nate Sedler
 */
public class Meow extends Command {

    public Meow() {

        this.name = "meow";
        this.help = "Gives a random pictures/videos of cats";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        String sURL = "https://aws.random.cat/meow";

        EmbedBuilder x = DefaultEmbed.embedDefault();

        x.setImage(GsonUtils.getJsonObject(sURL).get("file").getAsString());

        event.reply(x.build());
    }
}

