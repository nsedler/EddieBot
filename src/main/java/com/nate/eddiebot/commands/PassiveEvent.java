package com.nate.eddiebot.commands;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;

public class PassiveEvent {

    private final GenericMessageEvent event;
    private final User author;
    private final Message message;

    /**
     * Constructor for a PassiveEvent.
     *  @param  event
     *         The initial GenericMessageEvent
     */

    public PassiveEvent(GenericMessageEvent event){
        this.message = event.getChannel().getMessageById(event.getMessageId()).complete();
        this.event = event;
        this.author = this.message.getAuthor();
    }

    public void reply(String message){
        sendMessage(event.getChannel(), message);
    }

    public Message getMessage(){
        return message;
    }

    public User getAuthor(){
        return author;
    }

    private void sendMessage(MessageChannel chan, String message){
        chan.sendMessage(message).queue();
    }
}
