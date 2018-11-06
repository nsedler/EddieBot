package eddie.commands.essential;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.Categories;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

public class BotInfo extends Command {

    public BotInfo() {

        this.name = "BotInfo";
        this.aliases = new String[]{"info"};
        this.help = "Shows basic info about EddieBot";
        this.category = Categories.Misc;
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder em = new EmbedBuilder();

        em.setAuthor(String.valueOf(event.getSelfMember().getEffectiveName()), null, "https://seeklogo.com/images/P/pearl-jam-alive-logo-8FA34991E4-seeklogo.com.png");
        em.setColor(new Color(0,0,255));

        em.addField("__Total Guilds__", String.valueOf(event.getJDA().getGuildCache().size()), false);
        em.addField("__Total Users__", String.valueOf(event.getJDA().getUserCache().size()), false);

        event.reply(em.build());
    }
}
