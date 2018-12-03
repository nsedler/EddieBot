package com.nate.eddiebot.commands.administrator;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.helpful.Categories;
import com.nate.eddiebot.helpful.Permissions;
import com.nate.eddiebot.listener.events.BetterMessageEvent;

import net.dv8tion.jda.core.entities.Member;

public class Kick extends Command {

    public Kick(){
        this.name = "kick";
        this.arguments = "@user";
        this.help = "Kick a user from your guild";
        this.category = Categories.Administrator;
        this.userPermissions = Permissions.Admin;
        this.botPermissions = Permissions.KickUser;
    }

    @Override
    protected void execute(BetterMessageEvent event) {
        
        Member toKick = event.getMessage().getMentionedMembers().get(0);

        event.getGuild().getController().kick(toKick).queue();
        event.reply(event.getAuthor().getName() + " kicked " + toKick.getEffectiveName() + " from " + event.getGuild().getName());
	}
}