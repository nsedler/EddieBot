package com.nate.eddiebot.commands.fun;

import java.util.Arrays;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.Categories;
import com.nate.eddiebot.util.bot.DefaultEmbed;
import com.nate.eddiebot.util.web.GsonUtils;

import net.dv8tion.jda.core.EmbedBuilder;

/**
 * Returns a searched gif
 * 
 * @author Nate Sedler
 */
public class Gif extends Command {

    public Gif() {
        this.name = "gif";
        this.help = "Search for a gif";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        String[] args = event.getArgs();
        String sURL = "https://api.giphy.com/v1/gifs/random?api_key=Bxx5K3s6bY2XymQ3zxsap4KDcNbDxLT6&tag=" + Arrays.toString(args); //just a string

        EmbedBuilder x = DefaultEmbed.embedDefault();

        x.setImage(GsonUtils.getJsonObject(sURL).get("data").getAsJsonObject().get("images").getAsJsonObject().get("downsized_large")
                .getAsJsonObject().get("url").getAsString());

        event.reply(x.build());
    }
}
