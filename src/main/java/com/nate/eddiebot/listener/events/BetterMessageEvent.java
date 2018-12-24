package com.nate.eddiebot.listener.events;

/**
 *  Copyright 2018 Jonathan Augustine
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.GenericPrivateMessageEvent;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An wrapper class for {@link GenericMessageEvent}
 * & {@link GenericPrivateMessageEvent}
 * that allows for easier
 * responding to messages in a number of ways; namely, this class contains
 * methods for replying to messages regardless of the channel of origin, <br>
 * responding to events directly to a {@link PrivateChannel}.
 *
 * @author Jonathan Augustine
 */

public class BetterMessageEvent extends BetterEvent{

    private final MessageReceivedEvent event;
    private final User author;
    private final Message message;
    private final Member memberAuthor;
    private final String[] arguments;

    private static Logger logger = LoggerFactory.getLogger(BetterMessageEvent.class);

    /**
     * Constructor for a BetterMessageEvent.
     *  @param  event
     *         The initial GenericMessageEvent
     */
    public BetterMessageEvent(MessageReceivedEvent event){

        super(event);
        this.message = event.getMessage();
        this.event = event;
        this.author = event.getAuthor();
        this.memberAuthor = event.getMember();
        this.arguments = message.getContentStripped().trim().split("\\s+", -1);
    }

    @Override
    public void reply(String message){
        switch (this.event.getChannelType()) {
            case TEXT:
                this.event.getTextChannel().sendMessage(message).queue();
                break;
            case PRIVATE:
                this.author.openPrivateChannel().queue( (channel) ->
                        channel.sendMessage(message).queue()
                );
                break;
            default:
                logger.info("No channel");
                break;
        }
    }

    public void reply(String message, Consumer<Message> success){
        this.event.getChannel().sendMessage(message).queue(m -> success.accept(m));
    }

    public void reply(MessageEmbed embed){
        this.event.getChannel().sendMessage(embed).queue();
    }

    public void reply(MessageEmbed embed, Consumer<Message> success){
        this.event.getChannel().sendMessage(embed).queue(m -> success.accept(m));
    }

    public void reply(Message message){
        this.event.getTextChannel().sendMessage(message).queue();
    }

    @Override
    protected void privateReply(String message) {
        this.author.openPrivateChannel().queue(
                (channel) -> channel.sendMessage(message).queue()
        );
    }

    @Override
    protected boolean isPrivate() {
        return false;
    }

    public Message getMessage(){
        return message;
    }

    @Override
    protected Event getEvent() {
        return event;
    }

    public User getAuthor(){
        return author;
    }

    public final Member getMember() {
        return memberAuthor;
    }

    public Guild getGuild() {
        return this.event.getGuild();
    }

    public String[] getArgs(){
        return arguments;
    }

    public TextChannel getTextChannel(){
        return event.getTextChannel();
    }
}
