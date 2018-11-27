package com.nate.eddiebot.commands.essential;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nate.eddiebot.helpful.Categories;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.Permission;

public class InviteLink extends Command {

    public InviteLink(){
        this.name = "invite";
        this.help = "Gets an invite link for EddieBot";
        this.category = Categories.Misc;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply(EmbedUtils.embedMessage("__**[Click to invite Eddie to your server.](" + event.getJDA().asBot().getInviteUrl(Permission.ADMINISTRATOR) + ")**__"));
    }
}
