package eddie.commands.fun;

import eddie.helpful.JSONInfo;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ChuckNorris extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();

        if(message.equalsIgnoreCase(".chuck")){

            JSONInfo json = new JSONInfo();

            channel.sendMessage(json.JSONText("https://api.chucknorris.io/jokes/random", "value")).queue();
        }
    }
}
