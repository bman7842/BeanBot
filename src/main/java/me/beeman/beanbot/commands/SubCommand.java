package me.beeman.beanbot.commands;

import me.beeman.beanbot.utils.SimpleEmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * Created by brand on 1/13/2020.
 */
public interface SubCommand {

    String returnHelpMsg();

    /**
     * @param event Reference to the instance of GuildMessageReceivedEvent
     * @param args The additional arguments for the subcommand (Note: this list does not include the subcommand itself)
     */
    void onCommand(GuildMessageReceivedEvent event, String[] args);

    /**
     * the info for a specific subcommand
     */
    void sendSubCommandHelp(GuildMessageReceivedEvent event);
}
