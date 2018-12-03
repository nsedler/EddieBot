package com.nate.eddiebot.commands.administrator;

import java.util.concurrent.TimeUnit;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.helpful.Categories;
import com.nate.eddiebot.helpful.Permissions;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import net.dv8tion.jda.core.entities.MessageHistory;

public class Purge extends Command {

    public Purge(){
        this.name = "purge";
        this.arguments = "2 - 100";
        this.category = Categories.Administrator;
        this.botPermissions = Permissions.DeleteMessage;
        this.userPermissions = Permissions.Admin;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        int toDelete = Integer.parseInt(event.getArgs()[1]);

        MessageHistory mh = new MessageHistory(event.getTextChannel());

        try{

            if(toDelete >= 2 && toDelete <= 100){

                mh.retrievePast(toDelete + 1).queue(messages -> event.getMessage().getTextChannel().deleteMessages(messages).queue());
                event.reply(event.getAuthor().getName() + " cleared " + toDelete + " messages from " + event.getTextChannel().getName(), 
                (message) -> message.delete().queueAfter(5, TimeUnit.SECONDS));
            } else {

                event.reply("You must choose a number between 2 and 100!");
            }
        } catch (NumberFormatException e) {
            // if they type something wrong
            event.reply("Uh oh! Looks like you typed something wrong! Try .purge 10");
        }
    }
}
