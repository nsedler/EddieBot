package main.java;

import main.java.Administrator.Purge;
import main.java.Commands.*;

import main.java.Commands.PlayerControl;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.jetbrains.annotations.Nullable;

import javax.security.auth.login.LoginException;


public class main extends ListenerAdapter {
    public static void main(@Nullable String[] args){

        System.getenv();

        JDA discord = null;

        try {
            discord = new JDABuilder(AccountType.BOT).setToken(System.getenv("token")).buildBlocking();
        } catch (LoginException | IllegalArgumentException | InterruptedException e) {
            e.printStackTrace();
        }

        discord.addEventListener(new Misc());
        discord.addEventListener(new NoSwearing());
        discord.addEventListener(new Help());
        discord.addEventListener(new PlayerControl());
        discord.addEventListener(new Insult());
        discord.addEventListener(new TWSS());
        discord.addEventListener(new Purge());

        discord.getPresence().setGame(Game.playing(".help"));
    }
}

