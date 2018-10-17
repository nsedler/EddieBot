package main.java.Commands;

import main.java.Helpful.EmbedMsg;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class Help extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        String help = ".help - Shows commands\n" +
                ".hacks - Shows l33t haxor code code\n" +
                ".ping - Pong!\n" +
                ".retard - He really is\n" +
                ".insult @<member> - Make them cry\n" +
                ".music - List of all music commands";

        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();
        EmbedMsg embed = new EmbedMsg();


        if (message.startsWith(".help")) {

            channel.sendMessage(embed.sendEmbed(help)).queue();
        }
    }
}
