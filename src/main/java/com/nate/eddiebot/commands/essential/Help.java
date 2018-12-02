package com.nate.eddiebot.commands.essential;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.time.Instant;
import java.util.Objects;

public class Help extends Command {

    public Help() {

        this.name = "help";
        this.help = "Helps with commands";
        this.category = Categories.Help;
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder em = new EmbedBuilder()
                .setColor(new Color(0, 0, 255))
                .setFooter("EddieBot", null)
                .setTimestamp(Instant.now());


        for (Command command : event.getClient().getCommands()) {
            String fieldTitle = "";
            String fieldDesc = "";
            if (command.getName().isEmpty() || command.getName().equals(" ")) continue;
            if (command.isHidden()) continue;
            if (command.isOwnerCommand()) continue;
            if (command.getCategory().equals(Categories.Help)) {
                if (!Objects.equals(category, command.getCategory())) {

                    category = command.getCategory();
                    fieldTitle += "__**" + category.getName() + "**__";

                }
                fieldDesc += "`" + event.getClient().getPrefix() + command.getName() + (command.getArguments() == null ? "`" : " " + command.getArguments() + "`") + " - " + command.getHelp();
                em.addField(fieldTitle, fieldDesc, false);
            }
        }
        event.reply(em.build());
    }
}


