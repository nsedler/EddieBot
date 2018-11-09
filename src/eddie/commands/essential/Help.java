package eddie.commands.essential;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.util.Objects;


public class Help extends Command {

    public Help() {

        this.name = "help";
        this.help = "Helps with commands";
    }

    @Override
    public void execute(CommandEvent event) {

        EmbedBuilder em = new EmbedBuilder();

        em.setAuthor(String.valueOf(event.getSelfMember().getEffectiveName()), null, "https://seeklogo.com/images/P/pearl-jam-alive-logo-8FA34991E4-seeklogo.com.png");
        em.setColor(new Color(0, 0, 255));

        for (Command command : event.getClient().getCommands()) {
            String fieldTitle = "";
            String fieldDesc = "";
            if (command.getName().isEmpty() || command.getName().equals(" ")) continue;
            if (command.isHidden()) continue;
            if (!command.isOwnerCommand() || event.isOwner() || event.isOwner()) {
                if (!Objects.equals(category, command.getCategory())) {

                    category = command.getCategory();
                    fieldTitle += category == null ? "__**No Category**__" : "__**" + category.getName() + "**__";
                }
                fieldDesc += "`" + event.getClient().getPrefix() + command.getName() + (command.getArguments() == null ? "`" : " " + command.getArguments() + "`") + " - " + command.getHelp();
                em.addField(fieldTitle, fieldDesc, false);
            }
        }
        event.reply(em.build());
    }
}

