package com.nate.eddiebot.listener;

import com.nate.eddiebot.EddieBot;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EventDispatcher extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        EddieBot.sendToCommands(new BetterMessageEvent(event));
    }
}
