package com.nate.eddiebot.commands.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.entities.MessageEmbed;

public class Gif extends Command {

    public Gif() {

        this.name = "gif";
        this.arguments = "<query>";
        this.help = "Searches a gif on giphy.com";
        this.category = Categories.Fun;
    }

    @Override
    protected void execute(CommandEvent event) {

        String[] command = event.getMessage().getContentDisplay().split(" ", 2);

        command[1] = command[1].replace(" ", "+");

        String sURL = "https://api.giphy.com/v1/gifs/random?api_key=Bxx5K3s6bY2XymQ3zxsap4KDcNbDxLT6&tag=" + command[1]; //just a string

        WebUtils.ins.getJSONObject(sURL).async((json) -> {

            String embed = json.getJSONObject("data").getJSONObject("images").getJSONObject("downsized_large").getString("url");
            MessageEmbed em = EmbedUtils.embedImage(embed);

            event.reply(em);
        });
    }
}
