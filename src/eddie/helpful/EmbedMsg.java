package eddie.helpful;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.awt.*;
import java.util.Random;

public class EmbedMsg {

    public EmbedMsg(){

    }

    public Message sendEmbed(String message){

        Color color[];
        Random rng = new Random();

        color = new Color[]{
                Color.red, Color.BLACK, Color.WHITE, Color.green, Color.pink
        };

        int c = rng.nextInt(color.length);

        EmbedBuilder embed = new EmbedBuilder();

        embed.setAuthor("Eddie [BOT]");
        embed.setColor(color[c]);
        embed.setTitle("commands: ");
        embed.setDescription(message);

        MessageEmbed me = embed.build();
        MessageBuilder mb = new MessageBuilder();
        mb.setEmbed(me);
        Message m = mb.build();

        return mb.build();
    }
}
