package com.nate.eddiebot.commands.owner;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.BannedUsers;

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

        BannedUsers bUsers = new BannedUsers();
        String a = "";
        int lineNum = 1;

        try{

            for(String x : bUsers.readBannedUsers()){
                a += lineNum + ": " + x + " - " + event.getJDA().getUserById(Long.parseLong(x)).getName() + "\n";
                lineNum++;
            }
            event.reply(a);
        } catch(IllegalArgumentException e){
            event.reply("No one is banned from Eddie!");
        }
	}
}