package eddie.commands.misc;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.Categories;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;

public class Feedback extends Command {

    public Feedback() {

        this.name = "feedback";
        this.aliases = new String[]{"fb"};
        this.arguments = "<type of feedback> <feedback>";
        this.help = "Report a bug or give feedback on the bot";
        this.category = Categories.Misc;
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder em = new EmbedBuilder();

        em.setAuthor(String.valueOf(event.getSelfMember().getEffectiveName()), null, "https://seeklogo.com/images/P/pearl-jam-alive-logo-8FA34991E4-seeklogo.com.png");
        em.setColor(new Color(0, 0, 255));

        User owner = event.getJDA().retrieveUserById(event.getClient().getOwnerId()).complete();

        owner.openPrivateChannel().queue((channel) -> channel.sendMessage(event.getArgs()).queue());
        em.setDescription("Message sent to " + owner.getName() + "!");
        event.reply(em.build());
    }
}
