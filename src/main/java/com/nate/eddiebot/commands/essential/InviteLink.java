package com.nate.eddiebot.commands.essential;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.util.bot.Categories;
import com.nate.eddiebot.listener.events.BetterMessageEvent;

import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.Permission;

/**
 * Gets an invite link for others to use
 */
public class InviteLink extends Command {

    public InviteLink(){
        this.name = "invite";
        this.help = "Invite Eddie to other servers!";
        this.category = Categories.Misc;
    }

    @Override
    protected void execute(BetterMessageEvent event) {
        event.reply(EmbedUtils.embedMessage("__**[Click to invite Eddie to your server.](" + event.getJDA().asBot().getInviteUrl(Permission.ADMINISTRATOR) + ")**__"));
	}
    
}