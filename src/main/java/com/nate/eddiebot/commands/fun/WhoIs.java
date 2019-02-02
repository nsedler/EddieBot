package com.nate.eddiebot.commands.fun;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.Categories;
import com.nate.eddiebot.util.bot.DefaultEmbed;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nate Sedler
 */
public class WhoIs extends Command {

	public WhoIs(){
		this.name = "whoIs";
		this.category = Categories.Fun;
		this.help = "Tells information about a certain person or yourself";
	}

	@Override
	protected void execute(BetterMessageEvent event) {

		EmbedBuilder x = DefaultEmbed.embedDefault();
		String roles = "";
		System.out.println(Arrays.toString(event.getArgs()));

		if(event.getArgs().length == 0) {
			System.out.println("no no");
			Member who = event.getMember();
			for(int i = 0; i < who.getRoles().size(); i++){

				roles += who.getRoles().get(i).getName();
				if(i < who.getRoles().size() - 1)
					roles += ", ";
			}

			x.setThumbnail(who.getUser().getAvatarUrl());
			x.setAuthor(who.getEffectiveName(), who.getUser().getAvatarUrl(), who.getUser().getAvatarUrl());

			x.addField("User", "__**Name**__: " + who.getUser().getName()
					+ "\n" + "__**Nickname**__: " + who.getEffectiveName()
					+ "\n" + "__**ID**__: " + who.getUser().getIdLong(), true);
			x.addField("Account Created", who.getUser().getCreationTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), true);
			x.addField("Roles[" + who.getRoles().size() + "]", roles, true);
			x.addField("Date Joined", who.getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), true);

			event.reply(x.build());
		} else {

			Member who = event.getMessage().getMentionedMembers().get(0);

			for(int i = 0; i < who.getRoles().size(); i++){

				roles += who.getRoles().get(i).getName();
				if(i < who.getRoles().size() - 1)
					roles += ", ";
			}

			x.setThumbnail(who.getUser().getAvatarUrl());
			x.setAuthor(who.getEffectiveName(), who.getUser().getAvatarUrl(), who.getUser().getAvatarUrl());

			x.addField("User", "__**Name**__: " + who.getUser().getName()
					+ "\n" + "__**Nickname**__: " + who.getEffectiveName()
					+ "\n" + "__**ID**__: " + who.getUser().getIdLong(), true);
			x.addField("Account Created", who.getUser().getCreationTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), true);
			x.addField("Roles[" + who.getRoles().size() + "]", roles, true);
			x.addField("Date Joined", who.getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), true);

			event.reply(x.build());
		}
	}
}
