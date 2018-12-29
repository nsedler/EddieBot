package com.nate.eddiebot.commands.owner;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.BannedUsers;

/**
 * Unbans a previously banned user from Eddie
 * 
 * @author Nate Sedler
 */
public class RemoveBan extends Command {

	public RemoveBan(){
		this.name = "removeBan";
		this.ownerOnly = true;
		this.hidden = true;
	}

	@Override
	protected void execute(BetterMessageEvent event) {

		BannedUsers bUsers = new BannedUsers();
		long banId = Long.parseLong(event.getArgs()[1]);

		bUsers.removeBannedUser(banId);

		event.reply("The devs have unbanned " + event.getJDA().getUserById(banId).getName());
	}
}