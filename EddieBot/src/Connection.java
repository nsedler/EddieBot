import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Connection  extends ListenerAdapter {
    public static void main(String[] args) throws RateLimitedException {


            JDA discord = null;

            try {
                discord = new JDABuilder(AccountType.BOT).setToken(Constants.discordToken).buildBlocking();
            } catch (LoginException | IllegalArgumentException | InterruptedException e) {
                e.printStackTrace();
            }
            discord.addEventListener(new MessageResponder());
            discord.addEventListener(new NoSwearing());

            discord.getPresence().setGame(Game.playing(".help"));

        }
    }

