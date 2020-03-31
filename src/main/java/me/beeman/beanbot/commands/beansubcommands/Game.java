package me.beeman.beanbot.commands.beansubcommands;

import me.beeman.beanbot.commands.SubCommand;
import me.beeman.beanbot.utils.SimpleEmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;

public class Game implements SubCommand {

    @Override
    public String returnHelpMsg() {
        return "BeanBot offers several games you can play";
    }

    @Override
    public void sendSubCommandHelp(GuildMessageReceivedEvent event) {
        HashMap<String,String> subcmds = new HashMap<String,String>();
        subcmds.put("In Progress", "Commands subject to change/incomplete");

        SimpleEmbedBuilder simpleEmbedBuilder = new SimpleEmbedBuilder(event.getChannel());
        simpleEmbedBuilder.sendHelpMessage("game",null, subcmds,true);
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent event, String args[]) {
        if (args[0].equalsIgnoreCase("help")) {
            sendSubCommandHelp(event);
            return;
        }
    }

}
