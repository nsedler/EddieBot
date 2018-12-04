package com.nate.eddiebot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.util.bot.Categories;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.time.Instant;

public class MusicHelp extends Command {

    public MusicHelp(){
        this.name = "music";
        this.help = "Displays all music related commands";
        this.category = Categories.Help;
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder em = new EmbedBuilder()
                .setColor(new Color(0, 0, 255))
                .setFooter("EddieBot", null)
                .setTimestamp(Instant.now());

        em.setTitle("__**Music Commands**__");
        em.appendDescription("`.play <query> <link>` - Plays youtube songs\n\n");
        em.appendDescription("`.pplay <link>` - Plays youtube playlists\n\n");
        em.appendDescription("`.leave` - Leaves the voice channel\n\n");
        em.appendDescription("`.pause` - Pauses current song\n\n");
        em.appendDescription("`.stop` - Stops the current song and skips\n\n");
        em.appendDescription("`.skip` - Skips current song and starts the next\n\n");
        em.appendDescription("`.nowplaying` - Shows details on the current song\n\n");
        em.appendDescription("`.list` - Shows queue\n\n");
        em.appendDescription("`.volume <10> - <100>` - Sets the volume of EddieBot (Default is 35)\n\n");
        em.appendDescription("`.restart` - Restarts the current song\n\n");
        em.appendDescription("`.repeat` - Repeats the current song\n\n");
        em.appendDescription("`.reset` - Resets the player");

        event.reply(em.build());
    }
}
