package com.nate.eddiebot.commands.misc;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;

public class testing extends Command {

    public testing(){
        this.name = "test";
    }


    @Override
    protected void execute(BetterMessageEvent event) {
        event.reply("test");
    }
}
