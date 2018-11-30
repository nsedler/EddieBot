package com.nate.eddiebot.commands.essential;

import com.nate.eddiebot.EddieBot;
import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;

public class NewHelp extends Command {

    public NewHelp(){
        this.name = "helpme";
    }

    @Override
    protected void execute(BetterMessageEvent event) {
        for(Command c : EddieBot.getCommands()){
            event.reply(c.getName());
        }
    }
}
