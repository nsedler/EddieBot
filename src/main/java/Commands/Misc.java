package main.java.Commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Misc extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){

        String message = event.getMessage().getContentDisplay();
        MessageChannel channel = event.getChannel();
        String[] command = event.getMessage().getContentDisplay().split(" ", 2);

        if (message.equalsIgnoreCase(".ping") || message.equalsIgnoreCase("ping")) {

            String response = "Pong!";

            channel.sendMessage(response).queue();
        } else if (message.startsWith(".retard")) {

            String response = "<@394930557076897804> is a fucking retard!";
            channel.sendMessage(response).queue();
        } else if (message.startsWith(".hacks")) {

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

            channel.sendMessage(response).queue();
        } else if(message.equalsIgnoreCase("long")){

            String response = "That's what she said :joy: ";
            channel.sendMessage(response).queue();
        }
    }
}

