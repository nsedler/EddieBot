package main.java.Commands;

import main.java.Helpful.EmbedMsg;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Help extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        String help = ".help - Shows commands\n" +
                ".hacks - Shows l33t haxor code code\n" +
                ".ping - Pong!\n" +
                ".retard - He really is\n" +
                ".insult @<member> - Make them cry\n" +
                ".music - List of all music commands\n" +
                ".purge <2 - 100> - Deletes messages (admins only)\n" +
                ".jail <@user> - Jails them";

        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();
        EmbedMsg embed = new EmbedMsg();


        if (message.startsWith(".help")) {

            channel.sendMessage(embed.sendEmbed(help)).queue();
        }
    }
}
