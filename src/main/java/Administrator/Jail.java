package main.java.Administrator;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Jail extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();
        Member toMute = event.getMessage().getMentionedMembers().get(0);
        Role role = event.getGuild().getRoleById("499676837316919316");
        String[] command = event.getMessage().getContentDisplay().split(" ", 2);


        if(".jail".equalsIgnoreCase(command[0])){
            event.getGuild().getController().removeRolesFromMember(toMute, toMute.getRoles()).complete();
            event.getGuild().getController().addSingleRoleToMember(toMute, role).complete();

            channel.sendMessage(command[1] + " was muted by " + event.getAuthor().getName()).queue();
        }
    }
}
