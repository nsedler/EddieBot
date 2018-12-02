package com.nate.eddiebot.commands.essential;

import com.nate.eddiebot.EddieBot;
import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.time.Instant;
import java.util.Objects;

public class NewHelp extends Command {

    public NewHelp(){
        this.name = "helpme";
        this.hidden = true;
    }

    @Override
    protected void execute(BetterMessageEvent event) {
        String fieldTitle = "";
        String fieldDesc = "";

        EmbedBuilder em = new EmbedBuilder()
                .setColor(new Color(0, 0, 255))
                .setFooter("EddieBot", null)
                .setTimestamp(Instant.now());

        for(Command c : EddieBot.getCommands()){

            if(c.isHidden()) continue;
            if(!Objects.equals(category, c.getCategory())){

                category = c.getCategory();
                fieldTitle += "__**" + category.getName() + "**__";
            }
            fieldDesc += "`."  + c.getName() + (c.getArgs() == null ? "`" : " " + c.getArgs() + "`") + " - " + c.getHelp();
            em.addField(fieldTitle, fieldDesc, false);
        }
        event.reply(em.build());
    }
}
