package eddie.commands.fun;

import eddie.helpful.JSONInfo;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class FakeName extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event){


        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();

        if(message.equalsIgnoreCase(".fakeName")) {

            JSONInfo json = new JSONInfo();

            String region = String.valueOf(json.JSONText("http://uinames.com/api/?amount=1", "region"));
            String gender = String.valueOf(json.JSONText("http://uinames.com/api/?amount=1", "gender"));
            String name = String.valueOf(json.JSONText("http://uinames.com/api/?amount=1", "name"));
            String surname = String.valueOf(json.JSONText("http://uinames.com/api/?amount=1", "surname"));

            region = region.replace("DataMessage(", "");
            region = region.replace(")", "");
            gender = gender.replace("DataMessage(", "");
            gender = gender.replace(")", "");
            name = name.replace("DataMessage(", "");
            name = name.replace(")", "");
            surname = surname.replace("DataMessage(", "");
            surname = surname.replace(")", "");



            channel.sendMessage("Region: " + region + " \nGender: " + gender + " \nName: " + name + " \nSurname: " + surname).queue();
        }
    }
}
