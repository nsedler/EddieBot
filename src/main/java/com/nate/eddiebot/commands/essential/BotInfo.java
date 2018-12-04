package com.nate.eddiebot.commands.essential;

import java.awt.Color;
import java.lang.management.ManagementFactory;
import java.time.Instant;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.util.bot.Categories;
import com.nate.eddiebot.listener.events.BetterMessageEvent;

import net.dv8tion.jda.core.EmbedBuilder;

/**
 * Brief description of Eddie 
 * Gives Amount of guilds
 * Amount of users
 * Uptime
 */
public class BotInfo extends Command {

    public BotInfo(){
        this.name = "info";
        this.help = "Get information about EddieBot";
        this.category = Categories.Misc;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        final long duration = ManagementFactory.getRuntimeMXBean().getUptime();

        final long years = duration / 31104000000L;
        final long months = duration / 2592000000L % 12;
        final long days = duration / 86400000L % 30;
        final long hours = duration / 3600000L % 24;
        final long minutes = duration / 60000L % 60;
        final long seconds = duration / 1000L % 60;

        String uptime = "";
        uptime += years == 0 ? "" : years + " Year" + (years > 1 ? "s" : "") + ", ";
        uptime += months == 0 ? "" : months + " Month" + (months > 1 ? "s" : "") + ", ";
        uptime += days == 0 ? "" : days + " Day" + (days > 1 ? "s" : "") + ", ";
        uptime += hours == 0 ? "" : hours + " Hour" + (hours > 1 ? "s" : "") + ", ";
        uptime += minutes == 0 ? "" : minutes + " Minute" + (minutes > 1 ? "s" : "") + ", ";
        uptime += seconds == 0 ? "" : seconds + " Second" + (seconds > 1 ? "s" : "") + ", ";

        uptime = replaceLast(uptime, ", ", "");
        uptime = replaceLast(uptime, ",", " and");

        EmbedBuilder em = new EmbedBuilder()
                .setColor(new Color(0, 0, 255))
                .setFooter("EddieBot", null)
                .setTimestamp(Instant.now());

        em.setTitle("About Eddie!");
        em.setDescription("Eddie is a multi-purpose discord bot built using [JDA](https://github.com/DV8FromTheWorld/JDA)\n");
        em.appendDescription("Eddie has a variety of command categories from fun to administrator!\n");
        em.appendDescription("To get help, please type `.help`. Below there is some general information about [Eddie](https://github.com/nsedler/EddieBot!");

        em.addField("__Total Guilds__", String.valueOf(event.getJDA().getGuildCache().size()), true);
        em.addField("__Total Users__", String.valueOf(event.getJDA().getUserCache().size()), true);
        em.addField("__Uptime__", uptime, true);

        event.reply(em.build());
    }

    private static String replaceLast(final String text, final String regex, final String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }
    
}