package com.nate.eddiebot.commands.owner;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.BannedUsers;

public class BanUser extends Command {

    public BanUser(){
        this.name = "idBan";
        this.ownerOnly = true;
        this.hidden = true;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        BannedUsers bUsers = new BannedUsers();
        long banId = Long.parseLong(event.getArgs()[1]);
        bUsers.addBannedUser(banId);

        event.reply("The devs banned " + event.getJDA().getUserById(banId).getName());
    }
}
