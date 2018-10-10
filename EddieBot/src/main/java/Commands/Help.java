package main.java.Commands;

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

        String message = event.getMessage().getContentDisplay();
        final TextChannel botChannel = event.getGuild().getTextChannelsByName("eddie-bot-testing", true).get(0);
        MessageChannel channel = event.getChannel();

        if (channel == botChannel) {

            if (message.startsWith(".help")) {

                String mess = ".help - Shows commands\n" +
                        ".hacks - Shows l33t haxor code code\n" +
                        ".ping - Pong!\n" +
                        ".retard - He really is";

                EmbedBuilder embed = new EmbedBuilder();

                embed.setAuthor("Eddie [BOT]");
                embed.setColor(Color.red);
                embed.setTitle("Commands: ");
                embed.setDescription(mess);

                MessageEmbed me = embed.build();
                MessageBuilder mb = new MessageBuilder();
                mb.setEmbed(me);
                Message m = mb.build();

                botChannel.sendMessage(m).queue();
            }
        }
    }
}
