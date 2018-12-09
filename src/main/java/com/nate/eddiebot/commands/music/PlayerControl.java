package com.nate.eddiebot.commands.music;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.bandcamp.BandcampAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.SoundCloudAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.vimeo.VimeoAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.nate.eddiebot.util.bot.Categories;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.exceptions.PermissionException;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;

/**
 * Not mine, most from YUI bot
 *
 * Handles all music related commands.
 */
public class PlayerControl extends Command {

    public static final int DEFAULT_VOLUME = 35; //(0 - 150, where 100 is default max volume)

    private final AudioPlayerManager playerManager;
    private final Map<String, GuildMusicManager> musicManagers;

    public static Guild guild;
    String botName;


    public PlayerControl() throws IndexOutOfBoundsException {

        this.name = "play";
        this.aliases = new String[]{"play", "join", "skip", "leave", "fuckoff", "np", "nowplaying", "list", "pause", "stop", "shuffle", "pplay", "volume", "reset", "restart", "repeat"};
        this.help = "Shows all music commands";
        this.category = Categories.Fun;
        this.hidden = true;

        java.util.logging.Logger.getLogger("org.apache.http.client.protocol.ResponseProcessCookies").setLevel(Level.OFF);

        this.playerManager = new DefaultAudioPlayerManager();
        playerManager.registerSourceManager(new YoutubeAudioSourceManager());
        playerManager.registerSourceManager(new SoundCloudAudioSourceManager());
        playerManager.registerSourceManager(new BandcampAudioSourceManager());
        playerManager.registerSourceManager(new VimeoAudioSourceManager());
        playerManager.registerSourceManager(new TwitchStreamAudioSourceManager());
        playerManager.registerSourceManager(new HttpAudioSourceManager());
        playerManager.registerSourceManager(new LocalAudioSourceManager());

        musicManagers = new HashMap<String, GuildMusicManager>();
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        guild = event.getGuild();

        String[] command = event.getMessage().getContentDisplay().split(" ", 2);

        Guild guild = event.getGuild();
        GuildMusicManager mng = getMusicManager(guild);
        AudioPlayer player = mng.player;
        TrackScheduler scheduler = mng.scheduler;

        if (".join".equalsIgnoreCase(command[0])) {

            joinChannel(event);
        } else if (".leave".equals(command[0]) || ".fuckoff".equals(command[0])) {

            guild.getAudioManager().setSendingHandler(null);
            guild.getAudioManager().closeAudioConnection();

            synchronized (musicManagers) {

                scheduler.queue.clear();
                player.destroy();
                guild.getAudioManager().setSendingHandler(null);
                musicManagers.remove(guild.getId());
            }
        } else if (".play".equals(command[0])) {

            if (command.length == 1) {

                if (player.isPaused()) {

                    player.setPaused(false);
                    event.getTextChannel().sendMessage("Playback as been resumed.").queue();
                } else if (player.getPlayingTrack() != null) {

                    event.getTextChannel().sendMessage("Player is already playing!").queue();
                } else if (scheduler.queue.isEmpty()) {

                    event.getTextChannel().sendMessage("The current music queue is empty! Add something to the queue first!").queue();
                }
            } else {

                if (command[0].contains("youtube.com")) {

                    loadAndPlay(mng, event.getTextChannel(), command[1], false);
                    joinChannel(event);
                } else {

                    String input = "ytsearch: " + command[1];

                    loadAndPlay(mng, event.getTextChannel(), input, false);
                    joinChannel(event);
                }
            }
        } else if (".pplay".equals(command[0]) && command.length == 2) {

            joinChannel(event);
            loadAndPlay(mng, event.getTextChannel(), command[1], true);
        } else if (".skip".equals(command[0])) {

            scheduler.nextTrack();
            event.getTextChannel().sendMessage("The current track was skipped.").queue();
        } else if (".pause".equals(command[0])) {

            if (player.getPlayingTrack() == null) {

                event.getTextChannel().sendMessage("Cannot pause or resume player because no track is loaded for playing.").queue();
                return;
            }

            player.setPaused(!player.isPaused());
            if (player.isPaused())
                event.getTextChannel().sendMessage("The player has been paused.").queue();
            else
                event.getTextChannel().sendMessage("The player has resumed playing.").queue();
        } else if (".stop".equals(command[0])) {

            scheduler.queue.clear();
            player.stopTrack();
            player.setPaused(false);
            event.getTextChannel().sendMessage("Playback has been completely stopped and the queue has been cleared.").queue();
        } else if (".volume".equals(command[0])) {

            if (command.length == 1) {

                event.getTextChannel().sendMessage("Current player volume: **" + player.getVolume() + "**").queue();
            } else {

                try {

                    int newVolume = Math.max(10, Math.min(100, Integer.parseInt(command[1])));
                    int oldVolume = player.getVolume();
                    player.setVolume(newVolume);
                    event.getTextChannel().sendMessage("Player volume changed from `" + oldVolume + "` to `" + newVolume + "`").queue();
                } catch (NumberFormatException e) {

                    event.getTextChannel().sendMessage("`" + command[1] + "` is not a valid integer. (10 - 100)").queue();
                }
            }
        } else if (".restart".equals(command[0])) {

            AudioTrack track = player.getPlayingTrack();
            if (track == null)
                track = scheduler.lastTrack;

            if (track != null) {

                event.getTextChannel().sendMessage("Restarting track: " + track.getInfo().title).queue();
                player.playTrack(track.makeClone());
            } else {

                event.getTextChannel().sendMessage("No track has been previously started, so the player cannot replay a track!").queue();
            }
        } else if (".repeat".equals(command[0])) {

            scheduler.setRepeating(!scheduler.isRepeating());
            event.getTextChannel().sendMessage("Player was set to: **" + (scheduler.isRepeating() ? "repeat" : "not repeat") + "**").queue();
        } else if (".reset".equals(command[0])) {

            synchronized (musicManagers) {

                scheduler.queue.clear();
                player.destroy();
                guild.getAudioManager().setSendingHandler(null);
                musicManagers.remove(guild.getId());
            }

            mng = getMusicManager(guild);
            guild.getAudioManager().setSendingHandler(mng.sendHandler);
            event.getTextChannel().sendMessage("The player has been completely reset!").queue();

        } else if (".nowplaying".equals(command[0]) || ".np".equals(command[0])) {

            AudioTrack currentTrack = player.getPlayingTrack();
            if (currentTrack != null) {

                String title = currentTrack.getInfo().title;
                String position = getTimestamp(currentTrack.getPosition());
                String duration = getTimestamp(currentTrack.getDuration());

                String nowplaying = String.format("**Playing:** %s\n**Time:** [%s / %s]",
                        title, position, duration);

                event.getTextChannel().sendMessage(nowplaying).queue();
            } else
                event.getTextChannel().sendMessage("The player is not currently playing anything!").queue();
        } else if (".list".equals(command[0])) {

            Queue<AudioTrack> queue = scheduler.queue;
            synchronized (queue) {

                if (queue.isEmpty()) {

                    event.getTextChannel().sendMessage("The queue is currently empty!").queue();
                } else {

                    int trackCount = 0;
                    long queueLength = 0;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Current Queue: Entries: ").append(queue.size()).append("\n");
                    for (AudioTrack track : queue) {

                        queueLength += track.getDuration();
                        if (trackCount < 10) {

                            sb.append("`[").append(getTimestamp(track.getDuration())).append("]` ");
                            sb.append(track.getInfo().title).append("\n");
                            trackCount++;
                        }
                    }
                    sb.append("\n").append("Total Queue Time Length: ").append(getTimestamp(queueLength));

                    event.getTextChannel().sendMessage(sb.toString()).queue();
                }
            }
        } else if (".shuffle".equals(command[0])) {

            if (scheduler.queue.isEmpty()) {

                event.getTextChannel().sendMessage("The queue is currently empty!").queue();
                return;
            }

            scheduler.shuffle();
            event.getTextChannel().sendMessage("The queue has been shuffled!").queue();
        }
    }

    private void loadAndPlay(GuildMusicManager mng, final MessageChannel channel, String url, final boolean addPlaylist) {

        EmbedBuilder em = new EmbedBuilder();

        em.setAuthor(botName, null, "https://seeklogo.com/images/P/pearl-jam-alive-logo-8FA34991E4-seeklogo.com.png");
        em.setColor(new Color(0, 0, 255));

        try {
            if (!TrackScheduler.trask.isCancelled()) {

                TrackScheduler.trask.cancel(true);
            }
        } catch (NullPointerException e) {
            // really don't care for this
        }


        final String trackUrl;

        //Strip <>'s that prevent discord from embedding link resources
        if (url.startsWith("<") && url.endsWith(">"))
            trackUrl = url.substring(1, url.length() - 1);
        else
            trackUrl = url;

        playerManager.loadItemOrdered(mng, trackUrl, new AudioLoadResultHandler() {


            @Override
            public void trackLoaded(AudioTrack track) {

                em.setDescription("Adding to queue: " + track.getInfo().title);

                if (mng.player.getPlayingTrack() == null)
                    em.appendDescription("\nand the Player has started playing;");

                mng.scheduler.queue(track);
                channel.sendMessage(em.build()).queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {

                AudioTrack firstTrack = playlist.getSelectedTrack();
                List<AudioTrack> tracks = playlist.getTracks();


                if (firstTrack == null) {

                    firstTrack = playlist.getTracks().get(0);
                }

                if (addPlaylist) {

                    em.setDescription("Adding **" + playlist.getTracks().size() + "** tracks to queue from playlist: " + playlist.getName());

                    channel.sendMessage(em.build()).queue();
                    tracks.forEach(mng.scheduler::queue);
                } else {

                    em.setDescription("Adding to queue " + firstTrack.getInfo().title);
                    channel.sendMessage(em.build()).queue();
                    mng.scheduler.queue(firstTrack);
                }
            }

            @Override
            public void noMatches() {

                channel.sendMessage("Nothing found by " + trackUrl).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {

                channel.sendMessage("Could not play: " + exception.getMessage()).queue();
            }
        });
    }

    private GuildMusicManager getMusicManager(Guild guild) {

        String guildId = guild.getId();
        GuildMusicManager mng = musicManagers.get(guildId);
        if (mng == null) {

            synchronized (musicManagers) {

                mng = musicManagers.get(guildId);
                if (mng == null) {

                    mng = new GuildMusicManager(playerManager);
                    mng.player.setVolume(DEFAULT_VOLUME);
                    musicManagers.put(guildId, mng);
                }
            }
        }
        return mng;
    }

    private static String getTimestamp(long milliseconds) {

        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);

        if (hours > 0)
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        else
            return String.format("%02d:%02d", minutes, seconds);
    }

    private void joinChannel(BetterMessageEvent event) {


        VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
        Guild guild = event.getGuild();
        GuildMusicManager mng = getMusicManager(guild);

        VoiceChannel chan = connectedChannel;

        if (chan == null) {

            event.getTextChannel().sendMessage("You need to be in a voice channel.").queue();
        } else {

            guild.getAudioManager().setSendingHandler(mng.sendHandler);

            try {

                guild.getAudioManager().openAudioConnection(connectedChannel);
            } catch (PermissionException e) {

                if (e.getPermission() == Permission.VOICE_CONNECT) {

                    event.getTextChannel().sendMessage("Eddie does not have permission to connect to: " + chan.getName()).queue();
                }
            }
        }
    }
}