package me.beeman.beanbot;

import me.beeman.beanbot.commands.BeanCommand;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.security.auth.login.LoginException;

/**
 * Created by brand on 1/13/2020.
 */
public class Main {
    public static String prefix = "bean:";

    //TextChannel

    public static void main(String[] args) throws LoginException {
        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken("NjY5Njg4NTQ4ODQ0MzcxOTY5.XoKwjg.GSt6DSCoUPFkhMUTOcDWdOI8ipE")
                .addEventListeners(new BeanCommand())
                .setStatus(OnlineStatus.IDLE)
                .setActivity(Activity.listening("Gamer chatter"))
                .build();
    }


}
