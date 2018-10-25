package eddie.commands.administrator;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Jail extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        Member toMute = null;
        MessageChannel channel = event.getChannel();
        Role role = event.getGuild().getRoleById("499676837316919316");
        toMute = event.getMessage().getMentionedMembers().get(0);

        String[] command = event.getMessage().getContentDisplay().split(" ", 2);

        if(".jail".equalsIgnoreCase(command[0])){

            try {
                event.getGuild().getController().addSingleRoleToMember(toMute, role).complete();
            } catch(IndexOutOfBoundsException  | UnsupportedOperationException e){

                e.printStackTrace();
            }

            channel.sendMessage(command[1] + " was muted by " + event.getAuthor().getName()).queue();
        }
    }
}
