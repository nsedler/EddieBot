package com.nate.eddiebot.commands.owner;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.BannedUsers;

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

		BannedUsers bUsers = new BannedUsers();
		Long banId = null;

		try {
			banId = Long.parseLong(event.getArgs()[1]);
			try {
				event.getJDA().getUserById(banId).getName();
				bUsers.addBannedUser(banId);
				event.reply("The devs banned " + event.getJDA().getUserById(banId).getName());
			} catch (NullPointerException e) {
				event.reply("test123");
			}
		} catch (NumberFormatException e) {
			event.reply("You need to give and ID, not a name!You must give a valid ID!");
		}
	}
}
