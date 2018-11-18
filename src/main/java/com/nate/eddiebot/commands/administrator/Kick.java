package com.nate.eddiebot.commands.administrator;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;
import com.nate.eddiebot.helpful.Permissions;

public class Kick extends Command {

    public Kick() {

        this.name = "kick";
        this.arguments = "<user>";
        this.userPermissions = Permissions.Admin;
        this.botPermissions = Permissions.KickUser;
        this.category = Categories.Admin;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (event.getMessage().getMentionedMembers() == null)
            event.reply("You need to mention 1 or more user.");
        else {

            event.getGuild().getController().kick(event.getMessage().getMentionedMembers().get(0)).queue();
            event.reply(event.getAuthor() + " kicked " + event.getMessage().getMentionedMembers().get(0).getEffectiveName());
        }
    }
}
