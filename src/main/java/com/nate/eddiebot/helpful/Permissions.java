package com.nate.eddiebot.helpful;

import net.dv8tion.jda.core.Permission;

public class Permissions {

    public static Permission[] Admin = new Permission[]{Permission.ADMINISTRATOR};
    public static Permission[] AddPerms = new Permission[]{Permission.MANAGE_ROLES};
    public static Permission[] DeleteMessage = new Permission[]{Permission.MESSAGE_MANAGE};
    public static Permission[] KickUser = new Permission[]{Permission.KICK_MEMBERS};
}
