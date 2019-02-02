package com.nate.eddiebot.commands.essential;

import com.nate.eddiebot.EddieBot;
import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.DefaultEmbed;
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

    public Help(){
        this.name = "help";
        this.hidden = true;
        this.guildOnly = false;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        EmbedBuilder x = DefaultEmbed.embedDefault();

        for(Command c : EddieBot.getCommands()){

            String fieldTitle = "";
            String fieldDesc = "";

            if(c.isHidden() || c.isOwnerCommand()) continue;
            if (!Objects.equals(category, c.getCategory())) {

                category = c.getCategory();
                fieldTitle += category == null ? "__**No Category**__" : "__**" + category.getName() + "**__";
            }
            fieldDesc += "`."  + c.getName() + (c.getArgs() == null ? "`" : " " + c.getArgs() + "`") + " - " + c.getHelp();
            x.addField(fieldTitle, fieldDesc, false);
        }
        event.reply(x.build());
    }
}