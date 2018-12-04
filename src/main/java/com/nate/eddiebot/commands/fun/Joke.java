package com.nate.eddiebot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.util.bot.Categories;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.entities.MessageEmbed;

public class Joke extends Command {

    public Joke() {

        this.name = "joke";
        this.help = "Gives a random dad joke";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(CommandEvent event) {

        String sURL = "https://icanhazdadjoke.com/";

        WebUtils.ins.getJSONObject(sURL).async((json) -> {

            String embed = json.getString("joke");
            MessageEmbed em = EmbedUtils.embedMessage(embed);

            event.reply(em);
        });
    }
}


