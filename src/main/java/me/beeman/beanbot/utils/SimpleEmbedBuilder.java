package me.beeman.beanbot.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brand on 1/22/2020.
 */
public class SimpleEmbedBuilder {

    private User receiver;
    private TextChannel textChannelDelivery;
    private EmbedBuilder eb;

    public SimpleEmbedBuilder(TextChannel textChannelDelivery) {
        this.eb = new EmbedBuilder();
        this.textChannelDelivery = textChannelDelivery;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void deliver(boolean clearEmbedBuilder) {
        textChannelDelivery.sendTyping().queue();
        textChannelDelivery.sendMessage(eb.build()).queue();
        if (clearEmbedBuilder) {
            eb.clear();
        }
    }

    public void sendHelpMessage(String command, String footer, HashMap<String,String>commandList, boolean clearEmbedBuilder){
        eb.setTitle("CMD: " + command + ", Help:");
        if (footer != null) {
            eb.setFooter(footer);
        }
        for (Map.Entry subCmd : commandList.entrySet()) {
            eb.addField(subCmd.getKey().toString()+":"," - "+subCmd.getValue().toString(),false);
        }

        deliver(clearEmbedBuilder);
    }


    public void sendTestEmbed() {
        User user = getReceiver();

        eb.setTitle("SAMPLE EMBED TEST");
        eb.setDescription("This is an embed description");
        eb.addField("This is a field name", "this is the field's value", false);
        eb.addField("This is a second field", "this second field is inline", true);
        eb.setAuthor("This is the author");
        eb.setColor(0xcc6600);
        eb.setFooter("This is the footer ", user.getAvatarUrl());

        deliver(true);
    }

    public EmbedBuilder returnEmbedBuilder() {
        return eb;
    }

    public User getReceiver() {
        if (receiver == null) {
            throw new RuntimeException("User class missing from SimpleEmbedBuilder.");
        }
        return receiver;
    }

}
