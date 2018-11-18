package com.nate.eddiebot.commands.administrator;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;
import com.nate.eddiebot.helpful.Permissions;
import net.dv8tion.jda.core.entities.Message;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Purge extends Command {

    public Purge() {

        this.name = "purge";
        this.arguments = "<number>";
        this.help = "Purges the x amount of messages";
        this.category = Categories.Admin;
        this.botPermissions = Permissions.DeleteMessage;
        this.userPermissions = Permissions.Admin;
    }

    @Override
    protected void execute(CommandEvent event) {

        try {
            if (Integer.parseInt(event.getArgs()) <= 100 && Integer.parseInt(event.getArgs()) >= 2) {

                List<Message> msg = event.getTextChannel().getHistory().retrievePast(Integer.parseInt(event.getArgs())).complete();
                event.getTextChannel().deleteMessages(msg).queue();
                // deletes the message after 10 seconds
                event.reply(event.getAuthor().getName() + " purged " + event.getArgs() + " messages from " + event.getTextChannel().getName(), (message) -> message.delete().queueAfter(10, TimeUnit.SECONDS));
            } else {

                event.reply("The number must be between 2 and 100.");
            }
        } catch (NumberFormatException e) {
            // if they type something wrong
            event.reply("Uh oh! Looks like you typed something wrong! Try .purge 10");
        }

    }
}




