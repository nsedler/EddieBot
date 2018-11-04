package eddie.commands.administrator;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;

public class Jail extends Command {

    public Jail(Category c){

        this.name = "jail";
        this.arguments = "<user>";
        this.help = "Jails a user";
        this.category = c;
    }

    @Override
    protected void execute(CommandEvent event) {

        String[] command = event.getMessage().getContentDisplay().split(" ", 2);

        if(".jail".equalsIgnoreCase(command[0])){

            MessageChannel channel = event.getChannel();
            Role role = event.getGuild().getRoleById("499676837316919316");
            Member toMute = event.getMessage().getMentionedMembers().get(0);

            try {
                event.getGuild().getController().addSingleRoleToMember(toMute, role).complete();
            } catch(IndexOutOfBoundsException  | UnsupportedOperationException e){

                e.printStackTrace();
            }

            channel.sendMessage(command[1] + " was muted by " + event.getAuthor().getName()).queue();
        }

    }
}
