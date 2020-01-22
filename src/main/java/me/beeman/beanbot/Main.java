package me.beeman.beanbot;

import me.beeman.beanbot.commands.BeanCommand;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

/**
 * Created by brand on 1/13/2020.
 */
public class Main {
    public static String prefix = "bean:";

    public static void main(String[] args) throws LoginException {
        JDA jda = new JDABuilder(AccountType.BOT).setToken("NjY2NDMxMzA3ODU2NzQwMzcz.Xh0H5Q.S5MDapjWwZwG2JIu9wHEMhS1l4c").build();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setActivity(Activity.listening("Zane's queef"));

        jda.addEventListener(new BeanCommand());
    }
}
