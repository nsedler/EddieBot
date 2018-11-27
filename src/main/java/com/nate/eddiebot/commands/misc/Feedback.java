package com.nate.eddiebot.commands.misc;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;

public class Feedback extends Command {

    public Feedback() {

        this.name = "feedback";
        this.aliases = new String[]{"fb"};
        this.arguments = "<type of feedback> <feedback>";
        this.help = "Report a bug or give feedback on the bot";
        this.category = Categories.Misc;
    }

    @Override
    protected void execute(CommandEvent event) {
        MessageEmbed em;

        User owner = event.getJDA().retrieveUserById(event.getClient().getOwnerId()).complete();

        if(event.getArgs().isEmpty() || event.getArgs() == null)
            em = EmbedUtils.embedMessage("You must include something in your feedback!");
        else {
            owner.openPrivateChannel().queue((channel) -> channel.sendMessage(event.getArgs() + " from " + event.getAuthor().getName()).queue());
            em = EmbedUtils.embedMessage("Message sent to " + owner.getName() + "!");
        }
        event.reply(em);
    }
}
