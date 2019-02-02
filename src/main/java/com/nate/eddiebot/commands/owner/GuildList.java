package com.nate.eddiebot.commands.owner;

import com.nate.eddiebot.EddieBot;
import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.DefaultEmbed;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;

/**
 * @author Nate Sedler
 */
public class GuildList extends Command {

	public GuildList() {
		this.name = "guilds";
		this.ownerOnly = true;
		this.hidden = true;
	}

	@Override
	protected void execute(BetterMessageEvent event) {

		EmbedBuilder x = DefaultEmbed.embedDefault();
		String desc = "";

		for(Guild y : event.getJDA().getGuilds()){
			desc += y.getName() + ": " + y.getMemberCache().size() + "\n";
		}

		x.addField("__List of Guilds__", desc, true);

		event.reply(x.build());
	}
}
