package com.nate.eddiebot.commands.essential;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.time.Instant;

public class BotInfo extends Command {

    public BotInfo() {

        this.name = "BotInfo";
        this.aliases = new String[]{"info"};
        this.help = "Shows basic info about EddieBot";
        this.category = Categories.Misc;
    }

    @Override
    protected void execute(CommandEvent event) {

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

        em.addField("__Total Guilds__", String.valueOf(event.getJDA().getGuildCache().size()), false);
        em.addField("__Total Users__", String.valueOf(event.getJDA().getUserCache().size()), false);
        em.addField("__Uptime__", uptime, false);

        event.reply(em.build());
    }

    private static String replaceLast(final String text, final String regex, final String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }
}
