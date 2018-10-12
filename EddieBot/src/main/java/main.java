package main.java;

import main.java.Commands.*;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.jetbrains.annotations.Nullable;

import javax.security.auth.login.LoginException;

public class main extends ListenerAdapter  {
    public static void main(@Nullable String[] args) throws RateLimitedException {

        JDA discord = null;

        try {
            discord = new JDABuilder(AccountType.BOT).setToken(Constants.discordToken).buildBlocking();
        } catch (LoginException | IllegalArgumentException | InterruptedException e) {
            e.printStackTrace();
        }

        discord.addEventListener(new Misc());
        discord.addEventListener(new NoSwearing());
        discord.addEventListener(new Help());
        discord.addEventListener(new Player());
        discord.addEventListener(new Insult());

        discord.getPresence().setGame(Game.playing(".help"));
    }
}

