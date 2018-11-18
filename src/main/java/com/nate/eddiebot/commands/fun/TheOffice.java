package com.nate.eddiebot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.MessageEmbed;

public class TheOffice extends Command {

    public TheOffice() {

        this.name = "theoffice";
        this.help = "Gives a random gif from The Office";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(CommandEvent event) {

        String sURL = "https://api.giphy.com/v1/gifs/random?api_key=Bxx5K3s6bY2XymQ3zxsap4KDcNbDxLT6&tag=theoffice";
        WebUtils.ins.getJSONObject(sURL).async((json) -> {

            String embed = json.getJSONObject("data").getJSONObject("images").getJSONObject("downsized_large").getString("url");
            MessageEmbed em = EmbedUtils.embedImage(embed);

            Emote e = event.getJDA().getEmoteById("508750047102369811");

            event.reply(em, (message) -> message.addReaction(e).queue());
        });
    }
}

