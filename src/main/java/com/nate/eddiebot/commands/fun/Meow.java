package com.nate.eddiebot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.entities.MessageEmbed;

public class Meow extends Command {

    public Meow() {

        this.name = "meow";
        this.help = "Gives a random pictures/videos of cats";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(CommandEvent event) {

        String sURL = "https://aws.random.cat/meow"; //just a string

        WebUtils.ins.getJSONObject(sURL).async((json) -> {

            String embed = json.getString("file");
            MessageEmbed em = EmbedUtils.embedImage(embed);

            event.reply(em);
        });
    }
}

