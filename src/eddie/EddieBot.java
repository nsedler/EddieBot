package eddie;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import eddie.commands.administrator.Jail;
import eddie.commands.administrator.Purge;
import eddie.commands.essential.Ping;
import eddie.commands.fun.*;
import eddie.commands.essential.Help;
import eddie.commands.music.PlayerControl;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.jetbrains.annotations.Nullable;

import javax.security.auth.login.LoginException;
import java.io.IOException;


public class EddieBot extends ListenerAdapter {
    public static void main(@Nullable String[] args) throws IOException, LoginException, IllegalArgumentException, RateLimitedException {

        Command.Category Essential = new Command.Category("Essential");
        Command.Category Fun = new Command.Category("Fun");
        Command.Category Admin = new Command.Category("Admin");
        Command.Category Music = new Command.Category("Music");

        String token = System.getenv("token");

        EventWaiter waiter = new EventWaiter();

        CommandClientBuilder client = new CommandClientBuilder();
        client.setOwnerId("185063150557593600");
        client.setPrefix(".");
        client.useHelpBuilder(false);
        client.useDefaultGame();
        client.addCommands(

                // Administrator
                new Purge(Admin),
                new Jail(Admin),

                // Fun
                new ChuckNorris(Fun),
                new Insult(Fun),
                new Joke(Fun),
                new Meow(Fun),
                new TheOffice(Fun),
                new Woof(Fun),

                // Essential
                new Help(),
                new Ping(Essential),

                // Music
                new PlayerControl(Music)
                );

        JDABuilder jda = new JDABuilder(AccountType.BOT);

        jda.setToken(token);
        jda.setStatus(OnlineStatus.ONLINE);
        jda.setGame(Game.playing(".help for help"));
        jda.addEventListener(waiter);
        jda.addEventListener(client.build());
        jda.build();
    }
}

