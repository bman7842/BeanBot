package me.beeman.beanbot.commands.beansubcommands;

import me.beeman.beanbot.commands.SubCommand;
import me.beeman.beanbot.utils.SimpleEmbedBuilder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;

public class Dev implements SubCommand {

    @Override
    public String returnHelpMsg() {
        return "Some helpful developer tools";
    }

    @Override
    public void sendSubCommandHelp(GuildMessageReceivedEvent event) {
        HashMap<String,String> subcmds = new HashMap<String,String>();
        subcmds.put("defaultembed", "Prints a default embedded message, used to test SimpleEmbedBuilder");

        SimpleEmbedBuilder simpleEmbedBuilder = new SimpleEmbedBuilder(event.getChannel());
        simpleEmbedBuilder.sendHelpMessage("dev",null, subcmds,true);
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent event, String args[]) {
        if (args[0].equalsIgnoreCase("help")) {
            sendSubCommandHelp(event);
            return;
        }

        if (args[0].equalsIgnoreCase("defaultembed")) {
            SimpleEmbedBuilder simpleEmbedBuilderB = new SimpleEmbedBuilder(event.getChannel());
            simpleEmbedBuilderB.setReceiver(event.getAuthor());
            simpleEmbedBuilderB.sendTestEmbed();
            return;
        }
    }

}
