package com.nate.eddiebot;

import com.nate.eddiebot.commands.PassiveEvent;
import com.nate.eddiebot.commands.administrator.*;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.nate.eddiebot.commands.IPassive;
import com.nate.eddiebot.listener.EventDispatcher;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.Event;
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

    public static ArrayList<IPassive> Commands = new ArrayList<>(
            Arrays.asList(
                    new BadWordFilter()
            )
    );

    public static void main(@Nullable String[] args) throws IllegalArgumentException {

        String token = System.getenv("token");

        Logger logger = LoggerFactory.getLogger(EddieBot.class);
        EventWaiter waiter = new EventWaiter();

        WebUtils.setUserAgent("Mozilla/5.0 MenuDocs JDA Tutorial Bot/duncte123#1245");
        EmbedUtils.setEmbedBuilder(
                () -> new EmbedBuilder()
                        .setColor(new Color(0, 0, 255))
                        .setFooter("EddieBot", null)
                        .setTimestamp(Instant.now())
        );

//        CommandClientBuilder client = new CommandClientBuilder();
//        client.setOwnerId("185063150557593600");
//        client.setPrefix(".");
//        client.useHelpBuilder(false);
//        client.useDefaultGame();
//        client.addCommands(
//
//                // Owner
//                new Kill(),
//                new Eval(),
//
//                // Administrator
//                new Purge(),
//                new Jail(),
//                new Kick(),
//
//                // Fun
//                new ChuckNorris(),
//                new Gif(),
//                new Insult(),
//                new Joke(),
//                new Meow(),
//                new TheOffice(),
//                new Woof(),
//
//                // Misc
//                new Feedback(),
//
//                // Essential
//                new Help(),
//                new Ping(),
//                new BotInfo(),
//                new InviteLink(),
//
//                // Music
//                new PlayerControl(),
//                new MusicHelp()
//        );




        try {
            logger.info("Booting EddieBot");
            new JDABuilder(AccountType.BOT)
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

    public static void sendToPassives(PassiveEvent event) {
        for (IPassive p : Commands) {
            p.accept(event);
        }
    }
}