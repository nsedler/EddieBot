package com.nate.eddiebot;

import java.awt.Color;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import javax.security.auth.login.LoginException;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.commands.IPassive;
import com.nate.eddiebot.commands.administrator.BadWordFilter;
import com.nate.eddiebot.commands.administrator.Kick;
import com.nate.eddiebot.commands.administrator.Purge;
import com.nate.eddiebot.commands.essential.BotInfo;
import com.nate.eddiebot.commands.essential.Help;
import com.nate.eddiebot.commands.essential.InviteLink;
import com.nate.eddiebot.commands.essential.Ping;
import com.nate.eddiebot.commands.fun.*;
import com.nate.eddiebot.commands.misc.Feedback;
import com.nate.eddiebot.commands.music.MusicHelp;
import com.nate.eddiebot.commands.music.PlayerControl;
import com.nate.eddiebot.commands.owner.*;
import com.nate.eddiebot.listener.EventDispatcher;
import com.nate.eddiebot.listener.events.BetterMessageEvent;

import com.nate.eddiebot.util.bot.BannedUsers;
import com.nate.eddiebot.util.bot.BannedUsersNEW;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.RestAction;

public class EddieBot extends ListenerAdapter {

	// The JDA client
	private static JDA JDA_CLIENT;

	// Logger for logging shit
	private static Logger logger = LoggerFactory.getLogger(EddieBot.class);

	// List of passives
	private static ArrayList<IPassive> Passives = new ArrayList<>(
			Arrays.asList(
					new BadWordFilter()
			)
	);

	public static BannedUsersNEW bUsersNew = new BannedUsersNEW();

	// List of commands
	private static ArrayList<Command> Commands = new ArrayList<>(
			Arrays.asList(
			/*********************\
			|*       Owner       *|
			\*********************/
				new Kill(),
				new Eval(),
				new BanUser(),
				new BanList(),
				new RemoveBan(),
				new GuildList(),
			/*********************\
			|*       Admin       *|
			\*********************/    
				new Purge(), 
				new Kick(),
			/*********************\
			|*        Info       *|
			\*********************/   
				new Help(),
				new InviteLink(), 
				new BotInfo(), 
				new Ping(),
				new Feedback(),
				new MusicHelp(),
				new WhoIs(),
			/*********************\
			|*        Fun        *|
			\*********************/  
				new ChuckNorris(), 
				new TheOffice(),
				new Gif(),
				new Insult(),
				new Joke(),
				new Meow(),
				new Woof(),
				new PlayerControl()
			)
	);

	/**
	 * Runs everything I guess
	 */
	public static void main(@Nullable String[] args) throws IllegalArgumentException {

		// u no get my stuff
		String token = System.getenv("token");

		EmbedUtils.setEmbedBuilder(
				() -> new EmbedBuilder()
						.setColor(new Color(0, 0, 255))
						.setFooter("EddieBot", null)
						.setTimestamp(Instant.now())
		);

		RestAction.setPassContext(true); // enable context by default
		RestAction.DEFAULT_FAILURE = Throwable::printStackTrace;
		try {
			logger.info("Booting EddieBot");
			JDA_CLIENT = new JDABuilder(AccountType.BOT)
					.setToken(token)
					.setStatus(OnlineStatus.ONLINE)
					.setGame(Game.playing(".help for help"))
					.addEventListener(new EventDispatcher())
					.build().awaitReady();
			logger.info("EddieBot is now online!");
		} catch (LoginException | InterruptedException e) {
			logger.info("Provide the correct token please");
		}
	}

	/**
	 * Sends all of the passives to the EventDispatcher
	 * @param event the custom message event I made
	 */
	public static void sendToPassives(BetterMessageEvent event) {
		for (IPassive p : Passives) {
			p.accept(event);
		}
	}

	/**
	 * Sends all of the commands to the EventDispatcher
	 * @param event the custom message event I made
	 */
	public static void sendToCommands(BetterMessageEvent event) {

		String[] message = event.getMessage().getContentDisplay().split("\\s+");

		if (event.getAuthor().isBot()) return;
		if (message[0].charAt(0) != '.') return;

		String command = message[0].substring(1);

		for (Command c : Commands) {

			// Gets all aliases for commands
			if (command.equalsIgnoreCase(c.getName()) || Arrays.stream(c.getAliases()).parallel().anyMatch(command::equalsIgnoreCase)) {

				System.out.println("\n\n");
				logger.info("Running: " + c.getName());
				logger.info("\n" + (c.getAliases().length == 0 ? "N/A" : Arrays.toString(c.getAliases())) + "\n"
						+ (c.getHelp() == null ? "N/A" : c.getHelp() + "\n"
						+ (c.getCategory() == null ? "N/A" : c.getCategory().getName())));
				c.run(event);
			}
		}
	}

	/**
	 * Gets the jda client
	 * @return the jda client
	 */
	public static JDA getJda() {
		return EddieBot.JDA_CLIENT;
	}

	/**
	 * Gets the list of commands
	 * @return list of commands
	 */
	public static ArrayList<Command> getCommands() {
		return EddieBot.Commands;
	}

}