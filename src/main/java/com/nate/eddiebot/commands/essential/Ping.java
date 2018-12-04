package com.nate.eddiebot.commands.essential;

import java.awt.Color;
import java.time.Instant;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.util.bot.Categories;
import com.nate.eddiebot.listener.events.BetterMessageEvent;

import net.dv8tion.jda.core.EmbedBuilder;

/**
 * Gets the API ping and the host ping
 */
public class Ping extends Command {

    public Ping(){
        this.name = "ping";
        this.help = "Get the ping of the bot!";
        this.category = Categories.Misc;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        long currentTime = System.currentTimeMillis(); 

        // The first embed without host ping
        EmbedBuilder em = new EmbedBuilder()
                .setColor(new Color(0, 0, 255))
                .setFooter("EddieBot", null)
                .setTimestamp(Instant.now())
                .addField("JDA ping", String.valueOf(event.getJDA().getPing()) + "ms", true)
                .addField("Host Ping", "Pinging...", true);

        // The second embed almost with host ping
        EmbedBuilder em2 = new EmbedBuilder()
                .setColor(new Color(0, 0, 255))
                .setFooter("EddieBot", null)
                .setTimestamp(Instant.now())
                .addField("JDA ping", String.valueOf(event.getJDA().getPing()) + "ms", true);

        // sends the first embed then edits it and puts a field in with the hosts ping
        event.reply(em.build(), (m) -> m.editMessage(em2.addField("Host Ping", System.currentTimeMillis() - currentTime + "ms", true).build()).queue());
	}
    
}