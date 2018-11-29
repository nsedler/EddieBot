package com.nate.eddiebot.listener;

import com.nate.eddiebot.EddieBot;
import com.nate.eddiebot.commands.PassiveEvent;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EventDispatcher extends ListenerAdapter {

    public void onGenericMessageEvent(GenericMessageEvent event) {
        EddieBot.sendToPassives(new PassiveEvent(event));
        //In EddieBot.java, add an ArrayList<IPassive>
        //and store all the active ones there
    }
}
