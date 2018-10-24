package main.java;

import main.java.administrator.*;
import main.java.commands.*;

import main.java.commands.PlayerControl;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.jetbrains.annotations.Nullable;

import javax.security.auth.login.LoginException;


public class main extends ListenerAdapter {
    public static void main(@Nullable String[] args){


        JDA discord = null;


        try {
            discord = new JDABuilder(AccountType.BOT).setToken(System.getenv("token")).buildBlocking();
        } catch (LoginException | IllegalArgumentException | InterruptedException e) {
            e.printStackTrace();
        }

        discord.addEventListener(new Misc());
        discord.addEventListener(new SwearFilter());
        discord.addEventListener(new Help());
        discord.addEventListener(new PlayerControl());
        discord.addEventListener(new Insult());
        discord.addEventListener(new TWSS());
        discord.addEventListener(new Purge());
        discord.addEventListener(new Jail());
        discord.addEventListener(new Joke());
        discord.addEventListener(new Woof());

        discord.getPresence().setGame(Game.playing(".help"));
    }
}

