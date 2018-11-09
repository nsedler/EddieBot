package eddie;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import eddie.commands.owner.Eval;
import eddie.commands.owner.Kill;
import eddie.commands.administrator.Jail;
import eddie.commands.administrator.Purge;
import eddie.commands.essential.BotInfo;
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

        String token = System.getenv("token");

        EventWaiter waiter = new EventWaiter();

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

                // Fun
                new ChuckNorris(),
                new Gif(),
                new Insult(),
                new Joke(),
                new Meow(),
                new TheOffice(),
                new Woof(),

                // Essential
                new Help(),
                new Ping(),
                new BotInfo(),

                // Music
                new PlayerControl()
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

