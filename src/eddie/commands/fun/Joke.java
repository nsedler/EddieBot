package eddie.commands.fun;

import eddie.helpful.JSONInfo;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Joke extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event){


        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();

        if(message.equalsIgnoreCase(".joke")) {

            JSONInfo json = new JSONInfo();

            channel.sendMessage(json.JSONText("https://icanhazdadjoke.com/", "joke")).queue();
        }
    }
}


