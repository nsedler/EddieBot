package com.nate.eddiebot.commands;

import com.nate.eddiebot.EddieBot;

public abstract class Command {

    protected final String name;
    protected final String[] aliases;
    protected final String help;
    protected final String arguments;
    protected final boolean ownerOnly;
    protected final boolean hidden;
    protected final boolean nsfw;

    protected Command(String name, String[] aliases, String help, String arguments, boolean ownerOnly, boolean hidden, boolean nsfw){

        this.name = name;
        this.aliases = aliases;
        this.help = help;
        this.arguments = arguments;
        this.ownerOnly = ownerOnly;
        this.hidden = hidden;
        this.nsfw = nsfw;
    }

    protected abstract void execute(EddieBot bot, PassiveEvent event);
}
