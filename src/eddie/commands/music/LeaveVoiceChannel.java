package eddie.commands.music;

import net.dv8tion.jda.core.entities.Guild;

public class LeaveVoiceChannel implements Runnable{

    @Override
    public void run() {

        getGuild().getAudioManager().closeAudioConnection();
    }

    public Guild getGuild(){

        return PlayerControl.guild;
    }
}
