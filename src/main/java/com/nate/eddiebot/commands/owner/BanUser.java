package com.nate.eddiebot.commands.owner;

import com.nate.eddiebot.EddieBot;
import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.BannedUsers;
import com.nate.eddiebot.util.bot.BannedUsersNEW;

/**
 * Bans a user from using Eddie
 * 
 * @author Nate Sedler
 */
public class BanUser extends Command {

	public BanUser() {
		this.name = "idBan";
		this.ownerOnly = true;
		this.hidden = true;
	}

	@Override
	protected void execute(BetterMessageEvent event) {

		BannedUsersNEW bUsersNEW = EddieBot.bUsersNew;

		bUsersNEW.addUser(Long.parseLong(event.getArgs()[0]), event.getJDA().getUserById(event.getArgs()[0]).getName());

		event.reply("The devs banned " + event.getJDA().getUserById(event.getArgs()[0]).getName());
		System.out.println(bUsersNEW.toString());
	}
}
