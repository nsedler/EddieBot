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

	public BanUser(){
		this.name = "idBan";
		this.ownerOnly = true;
		this.hidden = true;
	}

	@Override
	protected void execute(BetterMessageEvent event) {

		BannedUsers bUsers = new BannedUsers();
		Long banId = null;

		try{
			event.getJDA().getUserById(banId).getName();
		try{

			banId = Long.parseLong(event.getArgs()[1]);
			bUsers.addBannedUser(banId);

			event.reply("The devs banned " + event.getJDA().getUserById(banId).getName());
		} catch(NumberFormatException e){
			event.reply("You need to give and ID, not a name!");
		}
		} catch(NullPointerException e){
			event.reply("You must give a valid ID!");
		}
	}
}
