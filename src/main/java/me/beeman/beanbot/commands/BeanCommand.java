package me.beeman.beanbot.commands;

import me.beeman.beanbot.Main;
import me.beeman.beanbot.commands.beansubcommands.Play;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by brand on 1/13/2020.
 */
public class BeanCommand extends ListenerAdapter {

    private HashMap<String, SubCommand> subCMDS;

    public BeanCommand() {
        this.subCMDS = new HashMap<String, SubCommand>();
        subCMDS.put("play", new Play());
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
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("BEAN BOT INFO");
            info.setDescription("Info relating to the extremely helpful and practical beanbot!");
            info.addField("Creator:", "Beeman", false); //Last boolean determines if its inline or not
            info.setColor(0xcc6600);
            info.setFooter("U look lame", event.getMember().getUser().getAvatarUrl());

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(info.build()).queue();
            info.clear(); //Saves system resources
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
}
