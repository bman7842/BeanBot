package me.beeman.beanbot.commands.beansubcommands;

import me.beeman.beanbot.commands.SubCommand;
import me.beeman.beanbot.music.PlayerManager;
import me.beeman.beanbot.utils.SimpleEmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.HashMap;

/**
 * Created by brand on 1/13/2020.
 */
public class Play implements SubCommand {

    @Override
    public String returnHelpMsg() {
        return "Commands related to playing audio";
    }

    @Override
    public void sendSubCommandHelp(GuildMessageReceivedEvent event) {
        HashMap<String,String> subcmds = new HashMap<String,String>();
        subcmds.put("In Progress", "Commands subject to change/incomplete");

        SimpleEmbedBuilder simpleEmbedBuilder = new SimpleEmbedBuilder(event.getChannel());
        simpleEmbedBuilder.sendHelpMessage("play",null, subcmds,true);
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent event, String args[]) {
        if (args[0].equalsIgnoreCase("help")) {
            sendSubCommandHelp(event);
            return;
        }

        //Joining the channel:
        TextChannel channel = event.getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();

        if (audioManager.isConnected()) {
            channel.sendMessage("I'm already connected to a channel").queue();
            return;
        }

        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("Please join a voice channel first").queue();
            return;
        }

        VoiceChannel voiceChannel = memberVoiceState.getChannel();
        Member selfMember = event.getGuild().getSelfMember();

        if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {
            channel.sendMessageFormat("I am missing permission to join %s", voiceChannel).queue();
            return;
        }

        audioManager.openAudioConnection(voiceChannel);
        channel.sendMessage("Joining your voice channel").queue();

        //Playing the track:
        PlayerManager manager = PlayerManager.getInstance();

        manager.loadAndPlay(event.getChannel(),"https://soundcloud.com/brandon-egger/fake-boy");

        manager.getGuildMusicManager(event.getGuild()).player.setVolume(10);
    }

}
