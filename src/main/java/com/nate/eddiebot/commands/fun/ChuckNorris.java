package com.nate.eddiebot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.entities.MessageEmbed;

public class ChuckNorris extends Command {

    public ChuckNorris() {

        this.name = "chuck";
        this.help = "Chuck norris Jokes";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(CommandEvent event) {

        String sURL = "https://api.chucknorris.io/jokes/random";

        WebUtils.ins.getJSONObject(sURL).async((json) -> {

            String embed = json.getString("value");
            MessageEmbed em = EmbedUtils.embedMessage(embed);

            event.reply(em);
        });
    }
}
