package com.nate.eddiebot.commands.fun;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.Categories;
import com.nate.eddiebot.util.bot.DefaultEmbed;
import com.nate.eddiebot.util.web.GsonUtils;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.EmbedBuilder;

/**
 * Adorable dogs
 *
 * @author Nate Sedler
 */
public class Woof extends Command {

    public Woof() {

        this.name = "woof";
        this.help = "Gives random pictures/videos of dogs";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        String sURL = "https://random.dog/woof.json";

        EmbedBuilder x = DefaultEmbed.embedDefault();

        x.setImage(GsonUtils.getJsonObject(sURL).get("url").getAsString());

        EmbedUtils.embedImage(GsonUtils.getJsonObject(sURL).get("url").getAsString());

        event.reply(EmbedUtils.embedImage(GsonUtils.getJsonObject(sURL).get("url").getAsString()));
    }
}
