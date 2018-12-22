package com.nate.eddiebot.commands;

import com.nate.eddiebot.EddieBot;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.BannedUsers;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Member;

import java.util.Arrays;

/**
 * Commands class for Eddie
 * This helps with running and executing code more productively
 *
 * @Author Nate Sedler
 */
public abstract class Command {

    /**
     * The name of the command, what users use to call the command.
     */
    protected String name = "null";

    /**
     * The aliases of a command, these function the same as calling the name.
     */
    protected String[] aliases = new String[0];

    /**
     * A brief description of what the command does.
     */
    protected String help = "no help found";

    /**
     * The {@link com.nate.eddiebot.commands.Command.Category Category} of the command.
     * This can perform checks that's not already checked by default
     */
    protected Category category = null;

    /**
     * The arguments the command accepts.
     */
    protected String arguments = null;

    /**
     * {@code true} Only the owner of the bot can use this command
     * {@code false} User doesn't have to be the owner of the bot
     * Default {@code false}
     */
    protected boolean ownerOnly = false;

    /**
     * {@code true} The command won't be shown in the help command
     * {@code false} The command will be shown in the help command
     */
    protected boolean hidden = false;

    /**
     * {@code true} The command may only be used in channels label NSFW
     * {@code false} The command can be used anywhere
     * Default {@code false}
     */
    protected boolean nsfw = false;

    /**
     * {@code true} if the command may only be used in a Guild
     * {@code false} if it may be used in both a Guild and DMs
     * Default {@code true}
     */
    protected boolean guildOnly = true;

    /**
     * Give the user a list of needed permissions to use the command
     */
    protected Permission[] userPermissions = new Permission[0];

    /**
     * Give the bot a list of needed permissions to use the command
     */
    protected Permission[] botPermissions = new Permission[0];

    /**
     * Performs what you want the command to do
     *
     * @param event the event called
     */
    protected abstract void execute(BetterMessageEvent event);

    /**
     * Performs a check then runs the command.
     *
     * @param event the event called
     */
    public void run(BetterMessageEvent event) {

        if (this.check(event)) {
            this.execute(event);
        }
    }

    /**
     * Checks for the Command with the given
     * {@link com.nate.eddiebot.commands.Command}
     * Will terminate and possibly respond with a failure message if any checks fail.
     *
     * @param event the event passed to the command
     * @return if the check has been passed
     */
    protected boolean check(BetterMessageEvent event) {

        if (event.getMember() != null) {
            Long userID = event.getAuthor().getIdLong();
            // checks if the user of the command is the owner of the bot
            if (this.ownerOnly && !userID.equals(Long.valueOf("185063150557593600"))) {
                event.reply("This command is for owners only.");
                return false;
            }

            BannedUsers bUsers = new BannedUsers();
            if(bUsers.readBannedUsers().contains(String.valueOf(event.getAuthor().getIdLong()))){
                event.getMessage().delete().queue();
                event.reply("The devs have decided it's best if you not use Eddie.");
                return false;
            }

            // checks if the user has correct perms to use command
            Member callMember = event.getMember();
            for (Permission p : this.userPermissions) {
                if (!callMember.hasPermission(p)) {
                    event.reply("You do not have the permissions: " + Arrays.toString(this.userPermissions));
                    return false;
                }
            }

            // checks if the bot has correct perms to carryout command
            Member selfBot = event.getGuild().getMember(EddieBot.getJda().getSelfUser());
            for (Permission p : this.botPermissions) {
                if (!selfBot.hasPermission(p)) {
                    return false;
                }
            }

            // checks if the channel is marked NSFW
            if (this.nsfw && !event.getMessage().getTextChannel().isNSFW()) {
                event.reply("This command can only be used in channels labeled NSFW.");
                return false;
            }
        }

        // checks if command will listen inside of DMs
        if (this.guildOnly && event.getMessage().getChannelType() != ChannelType.TEXT) {
            return false;
        }

        return true;
    }

    /**
     * Gets the {@link com.nate.eddiebot.commands.Command#name Command.name} for the Command.
     *
     * @return the name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the {@link com.nate.eddiebot.commands.Command#help Command.help} for the Command.
     *
     * @return the description of the command
     */
    public String getHelp() {
        return help;
    }

    /**
     * Gets the {@link com.nate.eddiebot.commands.Command#arguments Command.arguments} for the Command.
     *
     * @return The arguments of the command
     */
    public String getArgs() {
        return arguments;
    }

    /**
     * Gets the {@link com.nate.eddiebot.commands.Command#category Command.category} for the Command.
     *
     * @return the category of the command
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Gets the {@link com.nate.eddiebot.commands.Command#aliases Command.aliases} for the Command.
     *
     * @return The aliases for the Command
     */
    public String[] getAliases() {
        return aliases;
    }

    /**
     * Gets the {@link com.nate.eddiebot.commands.Command#hidden Command.hidden} for the Command.
     *
     * @return if the command is hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    public boolean isOwnerCommand() {
        return ownerOnly;
    }

    public static class Category {

        private final String name;

        public Category(String name) {
            this.name = name;
        }

        /**
         * Gets the name of the category
         *
         * @return the name of the category
         */
        public String getName() {
            return name;
        }
    }
}
