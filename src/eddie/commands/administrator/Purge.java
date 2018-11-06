package eddie.commands.administrator;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.Categories;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.util.List;

public class Purge extends Command {

    public Purge(){

        this.name = "purge";
        this.arguments = "<x>";
        this.help = "Purges the x amount of messages";
        this.category = Categories.Admin;
    }

    @Override
    protected void execute(CommandEvent event) {
        Boolean isAdmin = event.getMember().hasPermission(Permission.ADMINISTRATOR);
        Boolean isOwner = event.getMember().isOwner();
        MessageChannel channel = event.getChannel();
        String[] command = event.getMessage().getContentDisplay().split(" ", 2);

        if (isAdmin || isOwner) {
            if (".purge".equalsIgnoreCase(command[0])) {

                if (Integer.parseInt(command[1]) <= 100 && Integer.parseInt(command[1]) >= 2) {
                    List<Message> msg = channel.getHistory().retrievePast(Integer.parseInt(command[1])).complete();
                    event.getTextChannel().deleteMessages(msg).queue();

                    channel.sendMessage("Purged " + command[1] + " messages from " + channel.getName()).queue();
                } else {

                    channel.sendMessage("<@" + event.getAuthor().getId() + "> You must provide at least 2 or at most 100 messages to be deleted.").queue();
                }
            }
        } else if (!isAdmin) {

            channel.sendMessage("<@" + event.getAuthor().getId() + "> This command is for administrators only.");
        }
    }
}




