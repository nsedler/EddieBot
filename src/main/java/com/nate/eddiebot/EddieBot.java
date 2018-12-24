package com.nate.eddiebot;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.commands.music.PlayerControl;
import com.nate.eddiebot.commands.administrator.*;

import com.nate.eddiebot.commands.IPassive;
import com.nate.eddiebot.commands.essential.*;
import com.nate.eddiebot.commands.fun.*;
import com.nate.eddiebot.commands.misc.Feedback;
import com.nate.eddiebot.commands.misc.testing;
import com.nate.eddiebot.commands.music.MusicHelp;
import com.nate.eddiebot.commands.owner.BanUser;
import com.nate.eddiebot.commands.owner.Eval;
import com.nate.eddiebot.commands.owner.Kill;
import com.nate.eddiebot.listener.EventDispatcher;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.RestAction;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static ArrayList<Long> blockedUserIds = new ArrayList<>(
            Arrays.asList(
                    Long.valueOf("185063150557593600")
            )
    );

    // List of commands
    private static ArrayList<Command> Commands = new ArrayList<>(
            Arrays.asList(
            /*********************\
            |*       Owner       *|
            \*********************/
                new Kill(),
                new Eval(),
                new BanUser(),
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
                new PlayerControl(),
            /*********************\
            |*      Random       *|
            \*********************/  
                new testing()
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
        if (!message[0].substring(0, 1).equalsIgnoreCase(".")) return;

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