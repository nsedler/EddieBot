package com.nate.eddiebot;

import com.nate.eddiebot.commands.owner.*;
import com.nate.eddiebot.commands.administrator.*;
import com.nate.eddiebot.commands.fun.*;
import com.nate.eddiebot.commands.misc.*;
import com.nate.eddiebot.commands.essential.*;
import com.nate.eddiebot.commands.music.*;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.time.Instant;

public class EddieBot extends ListenerAdapter {
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

                // Music
                new PlayerControl()
        );

        try {
            logger.info("Booting EddieBot");
            new JDABuilder(AccountType.BOT)
                .setToken(token)
                .setStatus(OnlineStatus.ONLINE)
                .setGame(Game.playing(".help for help"))
                .addEventListener(waiter)
                .addEventListener(client.build())
                .build().awaitReady();
            logger.info("EddieBot is now online!");
        } catch (LoginException | InterruptedException e) {
            logger.info("Provide the correct token please");
        }
    }
}

