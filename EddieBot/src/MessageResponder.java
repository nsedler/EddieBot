import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class MessageResponder extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event){

        String message = event.getMessage().getContentDisplay();
        final TextChannel botChannel = event.getGuild().getTextChannelsByName("eddie-bot-testing",true).get(0);
        MessageChannel channel = event.getChannel();

        if(channel == botChannel) {

            if (message.startsWith(".ping") || message.startsWith("Ping")) {

                String response = "Pong!";

                botChannel.sendMessage(response).queue();
            } else if (message.startsWith(".retard")) {

                String response = "<@394930557076897804> is a fucking retard!";
                botChannel.sendMessage(response).queue();
            } else if (message.startsWith("channel")) {

                String response = "The channel is: " + channel;

                botChannel.sendMessage(response).queue();
            } else if(message.startsWith(".hacks")){

                String response = "```java\n" +
                        "public class Hacker{\n" +
                        "\n" +
                        "    private int hax;\n" +
                        "\n" +
                        "    public Hacker(){\n" +
                        "\n" +
                        "        hax = 1337;\n" +
                        "    }\n" +
                        "\n" +
                        "    public void hacked(String recipient){\n" +
                        "\n" +
                        "        System.out.println(recipient + \" has been l33t haxored\" + hax);\n" +
                        "    }\n" +
                        "}\n" +
                        "```";

                botChannel.sendMessage(response).queue();
            } else if(message.startsWith(".help")){

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
