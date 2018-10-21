package main.java.Commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class TWSS extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event){

        String message=event.getMessage().getContentDisplay();
        String id=event.getAuthor().getId();
        Boolean isBot=event.getAuthor().isBot();
        MessageChannel channel=event.getChannel();

        String twss[] = new String[]{"No thanks, I'm good",
                "My mother's coming",
                "Wow, that is really hard",
                "You really think you can go all day long",
                "Why did you get it so big",
                "Does the skin look red and swollen",
                "You should put butter on it",
                "Dwight eats grapes",
                "You already did me",
                "Put this matter to bed",
                "I can't stay on top of you 24/7",
                "They taste so good in my mouth",
                "I want you to think about it long and hard",
                "Let's just blow this party off",
                "Why is this so hard",
                "I need two men on this job",
                "Dip it in water so it'll slide down your gullet more easily",
                "Can you make that straighter",
                "And up comes the toolbar",
                "That's what I said",
                "I say things like that to lighten the tension when things sort of get hard",
                "Come again",
                "And you were directly under her the entire time",
                "Well, you are hardly my first",
                "Force it in as deep as you can",
                "It was easy to get in but impossible to rise up",
                "Hold it in your mouth if you can't swallow",
                "It's gonna be tight",
                "It squeaks when you bang it",
                "Don't make it harder than it needs to be",
                "Dwight, get out of my nook!",
                "Oscar, would you reach over and touch his thing",
                "This is huge",
                "I can't force you to go down, but I can entice you",
                "Instead you screwed me",
                "Put it away",
                "You need to get back on top",
                "Oh. Eso es lo que dice, el!",
                "Get it up. That's what",
                "The trick is to do it face down with the post in your mouth",
                "Michael, you're making this harder than it has to be",
                "Oscar is my queen. That's easy, gimme a hard one",
                "Comedy is a place where the mind goes to tickle itself",
                "I'm not saying it won't be hard, but we can make it work.",
                "This is going to feel so good getting this thing off my chest",
                "There's no way you guys are making this magic with just your mouths.",
                "Michael, I can't believe you came."
        };

        for (int i = 0; i < twss.length; i++) {

            if (isBot) {

                break;
            } else {

                if (message.equalsIgnoreCase(twss[i]) || message.contains(twss[i])) {

                    String response = "<@" + id + "> That's what she said!";
                    channel.sendMessage(response).queue();
                }
            }


        }
    }
}
