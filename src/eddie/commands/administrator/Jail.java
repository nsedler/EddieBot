package eddie.commands.administrator;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.Categories;
import eddie.helpful.Permissions;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

import java.util.List;

public class Jail extends Command {

    public Jail() {

        this.name = "jail";
        this.arguments = "<user>";
        this.help = "Jails a user";
        this.category = Categories.Admin;
        this.botPermissions = Permissions.AddPerms;
        this.userPermissions = Permissions.Admin;
    }

    @Override
    protected void execute(CommandEvent event) {

        List<Role> role = event.getGuild().getRoles();

        Role jailRole = null;

        for (Role roleName : role) {

            if (roleName.getName().equalsIgnoreCase("jail") || roleName.getName().equalsIgnoreCase("jailed")) {

                jailRole = roleName;
            }
        }

        if (jailRole == null) {

            event.reply("No role called 'jail' or 'jailed' found.");
        } else {

            Member toMute = null;
            try {
                toMute = event.getMessage().getMentionedMembers().get(0);
            } catch (IndexOutOfBoundsException e) {
                event.reply("You need to mention a user!");
            }

            try {
                if(toMute.equals(event.getSelfMember()))
                    event.reply("You can't mute me!");
                else {
                    event.getGuild().getController().addSingleRoleToMember(toMute, jailRole).complete();
                    event.reply(toMute.getEffectiveName() + " was muted by " + event.getAuthor().getName());
                }
            } catch (IndexOutOfBoundsException | IllegalArgumentException | NullPointerException e) {
                // Don't care for these
            }
        }
    }
}
