package com.nate.eddiebot.commands.essential;

import com.nate.eddiebot.EddieBot;
import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.time.Instant;
import java.util.Objects;

/**
 * Replies with an embed of all commands unless they are for owners or are hidden
 *
 * @author Nate Sedler
 */
public class Help extends Command {

    public Help() {
        this.name = "help";
        this.hidden = true;
        this.guildOnly = false;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        EmbedBuilder em = new EmbedBuilder()
                .setColor(new Color(0, 0, 255))
                .setFooter("EddieBot", null)
                .setTimestamp(Instant.now());

        for (Command c : EddieBot.getCommands()) {

            String fieldTitle = "";
            String fieldDesc = "";

            if (c.isHidden() || c.isOwnerCommand()) continue;
            System.out.println(c.getCategory());
            if (!Objects.equals(category, c.getCategory())) {

                category = c.getCategory();
                fieldTitle = "__**" + category.getName() + "**__";
            }
            fieldDesc += "`." + c.getName() + (c.getArgs() == null ? "`" : " " + c.getArgs() + "`") + " - " + c.getHelp();
            em.addField(fieldTitle, fieldDesc, false);
        }
        event.reply(em.build());
    }
}
