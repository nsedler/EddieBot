package eddie;

import eddie.commands.administrator.*;

import eddie.commands.fun.*;
import eddie.commands.misc.Help;
import eddie.commands.misc.Misc;
import eddie.commands.music.PlayerControl;
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

        // administrator
        discord.addEventListener(new SwearFilter());
        discord.addEventListener(new Purge());
        discord.addEventListener(new Jail());

        // fun
        discord.addEventListener(new Insult());
        discord.addEventListener(new TWSS());
        discord.addEventListener(new Joke());
        discord.addEventListener(new Woof());
        discord.addEventListener(new Meow());
        discord.addEventListener(new ChuckNorris());

        // misc
        discord.addEventListener(new Misc());
        discord.addEventListener(new Help());

        // music
        discord.addEventListener(new PlayerControl());

        // discord integration
        discord.getPresence().setGame(Game.playing(".help"));
    }
}

