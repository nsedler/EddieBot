package com.nate.eddiebot.commands.fun;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.Categories;
import com.nate.eddiebot.util.bot.DefaultEmbed;
import com.nate.eddiebot.util.web.GsonUtils;

import net.dv8tion.jda.core.EmbedBuilder;

/**
 * Gets a random gif from The Office
 *
 * @author Nate Sedler
 */
public class TheOffice extends Command {

    public TheOffice() {
        this.name = "theoffice";
        this.help = "Random gif from __*The Office*__";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        String sURL = "https://api.giphy.com/v1/gifs/random?api_key=Bxx5K3s6bY2XymQ3zxsap4KDcNbDxLT6&tag=theoffice";

        EmbedBuilder x = DefaultEmbed.embedDefault();

        x.setImage(GsonUtils.getJsonObject(sURL).get("data").getAsJsonObject().get("images").getAsJsonObject().get("downsized_large")
                .getAsJsonObject().get("url").getAsString());

        event.reply(x.build());
    }
}
