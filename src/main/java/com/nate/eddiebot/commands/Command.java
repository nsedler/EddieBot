package com.nate.eddiebot.commands;

import com.nate.eddiebot.EddieBot;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command {

    protected  String name;
    protected  String[] aliases;
    protected  String help;
    protected  String arguments;
    protected  boolean ownerOnly;
    protected  boolean hidden;
    protected  boolean nsfw;
    protected  boolean guildOnly;

    protected Permission[] userPermissions = new Permission[0];
    protected Permission[] botPermissions = new Permission[0];

    protected abstract void execute(BetterMessageEvent event);

    public void run(BetterMessageEvent event){

        if(this.check(event)) {
            this.execute(event);
        }
    }

    protected boolean check(BetterMessageEvent event){

        if(event.getMember() != null){

            Member callMember  = event.getMember();
            for(Permission p : this.userPermissions){
                if(!callMember.hasPermission(p)){
                    return false;
                }
            }

            Member selfBot = event.getGuild().getMember(EddieBot.getJda().getSelfUser());

            for (Permission p : this.botPermissions) {
                if (!selfBot.hasPermission(p)) {
                    return false;
                }
            }
        }

        if (this.guildOnly && event.getMessage().getChannelType() != ChannelType.TEXT) {
            return false;
        }

        return true;
    }
}
