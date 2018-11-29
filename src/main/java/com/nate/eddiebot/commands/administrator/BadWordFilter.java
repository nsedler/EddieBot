package com.nate.eddiebot.commands.administrator;

import com.nate.eddiebot.commands.IPassive;
import com.nate.eddiebot.commands.PassiveEvent;

public class BadWordFilter implements IPassive {

    @Override
    public void accept(PassiveEvent event) {
        if(event.getMessage().getContentDisplay().equalsIgnoreCase("123")){
            event.reply("test");
            System.out.println("true");
        }

    }

    @Override
    public boolean dead() {
        return false;
    }
}
