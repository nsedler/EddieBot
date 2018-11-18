package com.nate.eddiebot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.entities.MessageEmbed;

public class Woof extends Command {

    public Woof() {

        this.name = "woof";
        this.help = "Gives random pictures/videos of dogs";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(CommandEvent event) {

        String sURL = "https://random.dog/woof.json"; //just a string

        WebUtils.ins.getJSONObject(sURL).async((json) -> {

            String embed = json.getString("url");
            MessageEmbed em = EmbedUtils.embedImage(embed);

            event.reply(em);
        });
    }
}
