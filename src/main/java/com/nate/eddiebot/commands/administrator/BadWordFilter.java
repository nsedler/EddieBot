package com.nate.eddiebot.commands.administrator;

import com.nate.eddiebot.commands.IPassive;
import com.nate.eddiebot.listener.events.BetterMessageEvent;

public class BadWordFilter implements IPassive {


    @Override
    public void accept(BetterMessageEvent event) {
        if(event.getMessage().getContentDisplay().equalsIgnoreCase("123")){
            System.out.println("BadWord");
            event.reply("BadWord");
        }
    }

    @Override
    public boolean dead() {
        return false;
    }
}
