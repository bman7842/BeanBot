package me.beeman.beanbot.commands;

import me.beeman.beanbot.Main;
import me.beeman.beanbot.commands.beansubcommands.Dev;
import me.beeman.beanbot.commands.beansubcommands.Game;
import me.beeman.beanbot.commands.beansubcommands.Play;
import me.beeman.beanbot.utils.SimpleEmbedBuilder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.*;

/**
 * Created by brand on 1/13/2020.
 */
public class BeanCommand extends ListenerAdapter {

    private HashMap<String, SubCommand> subCMDS;

    public BeanCommand() {
        this.subCMDS = new HashMap<String, SubCommand>();
        subCMDS.put("play", new Play());
        subCMDS.put("dev", new Dev());
        subCMDS.put("game", new Game());
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (!args[0].equalsIgnoreCase(Main.prefix)) {
            return;
        }

        if (args.length <=1) {
            //send some sort of message about typing bean: help for help
            return;
        }

        if (args[1].equalsIgnoreCase("help") || args[1].equalsIgnoreCase("info")) {
            sendHelpMsg(event);
            return;
        }

        String subCMD = args[1].toLowerCase();

        Vector<String> shortArgs = new Vector<String>();
        shortArgs.addAll(Arrays.asList(args));
        shortArgs.remove(0);
        shortArgs.remove(0);
        args = shortArgs.toArray(new String[0]);

        try {
            subCMDS.get(subCMD).onCommand(event, args);
        } catch (Exception e) {
            System.out.println("Implement error message system!!! - Failed to run subcommand");
        }
    }

    private void sendHelpMsg(GuildMessageReceivedEvent event) {
        SimpleEmbedBuilder simpleEmbedBuilder = new SimpleEmbedBuilder(event.getChannel());

        HashMap<String, String> cmdHelpList = new HashMap<String, String>();

        for (String subCmd : subCMDS.keySet()) {
            cmdHelpList.put(subCmd, subCMDS.get(subCmd).returnHelpMsg());
        }

        simpleEmbedBuilder.sendHelpMessage("BeanBot","Note: each sub-command has an additional help menu you can access by typing bean: {cmd} help", cmdHelpList, true);
    }
}
