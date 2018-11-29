package com.nate.eddiebot;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.commands.administrator.*;

import com.nate.eddiebot.commands.IPassive;
import com.nate.eddiebot.commands.essential.BotInfo;
import com.nate.eddiebot.commands.essential.Help;
import com.nate.eddiebot.commands.essential.InviteLink;
import com.nate.eddiebot.commands.essential.Ping;
import com.nate.eddiebot.commands.fun.*;
import com.nate.eddiebot.commands.misc.Feedback;
import com.nate.eddiebot.commands.misc.testing;
import com.nate.eddiebot.commands.music.MusicHelp;
import com.nate.eddiebot.commands.music.PlayerControl;
import com.nate.eddiebot.commands.owner.Eval;
import com.nate.eddiebot.commands.owner.Kill;
import com.nate.eddiebot.listener.EventDispatcher;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class EddieBot extends ListenerAdapter {

    private static JDA JDA_CLIENT;

    public static ArrayList<IPassive> Passives = new ArrayList<>(
            Arrays.asList(
                    new BadWordFilter()
            )
    );

    public static ArrayList<Command> Commands = new ArrayList<>(
            Arrays.asList(
                    new testing()
            )
    );

    public static void main(@Nullable String[] args) throws IllegalArgumentException {

        String token = System.getenv("token");

        Logger logger = LoggerFactory.getLogger(EddieBot.class);

        WebUtils.setUserAgent("Mozilla/5.0 MenuDocs JDA Tutorial Bot/duncte123#1245");
        EmbedUtils.setEmbedBuilder(
                () -> new EmbedBuilder()
                        .setColor(new Color(0, 0, 255))
                        .setFooter("EddieBot", null)
                        .setTimestamp(Instant.now())
        );



        CommandClientBuilder client = new CommandClientBuilder();
        client.setOwnerId("185063150557593600");
        client.setPrefix(".");
        client.useHelpBuilder(false);
        client.useDefaultGame();
        client.addCommands(

                // Owner
                new Kill(),
                new Eval(),

                // Administrator
                new Purge(),
                new Jail(),
                new Kick(),

                // Fun
                new ChuckNorris(),
                new Gif(),
                new Insult(),
                new Joke(),
                new Meow(),
                new TheOffice(),
                new Woof(),

                // Misc
                new Feedback(),

                // Essential
                new Help(),
                new Ping(),
                new BotInfo(),
                new InviteLink(),

                // Music
                new PlayerControl(),
                new MusicHelp()
        );



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

    public static void sendToPassives(BetterMessageEvent event) {
        for (IPassive p : Passives) {
            p.accept(event);
        }
    }

    public static void sendToCommands(BetterMessageEvent event){

        String message = event.getMessage().getContentDisplay();
        if(!message.substring(0,  1).equalsIgnoreCase(".")) return;

        for(Command c : Commands){
            c.run(event);
        }
    }

    public static JDA getJda() {
        return EddieBot.JDA_CLIENT;
    }
}