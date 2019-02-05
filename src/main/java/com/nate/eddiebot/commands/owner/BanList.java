package com.nate.eddiebot.commands.owner;

import com.nate.eddiebot.EddieBot;
import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.BannedUsers;
import com.nate.eddiebot.util.bot.BannedUsersNEW;

import java.util.Map;

/**
 * Prints out list of every banned user
 * 
 * @author Nate Sedler
 */
public class BanList extends Command {

    public BanList(){
        this.name = "ViewBans";
        this.ownerOnly = true;
        this.hidden = true;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        BannedUsersNEW bUsersNEW = EddieBot.bUsersNew;
        String a = "";
        int lineNum = 1;

        try{

            for(Map.Entry<Long, String> entry : bUsersNEW.getMap().entrySet()) {
                System.out.println(lineNum + ": " + entry.getValue() + " - " + entry.getKey());
                a += lineNum + ": " + entry.getValue() + " - " + entry.getKey();
            }
            event.reply(a);
        } catch(IllegalArgumentException e){
            event.reply("No one is banned from Eddie!");
        }
	}
}