package com.nate.eddiebot.util.bot;

import java.awt.Color;
import java.time.Instant;

import net.dv8tion.jda.core.EmbedBuilder;

public class DefaultEmbed {

    public static EmbedBuilder embedDefault() {

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(0, 0, 255));
        embed.setFooter("EddieBot", null);
        embed.setTimestamp(Instant.now());

        return embed;
    }
}