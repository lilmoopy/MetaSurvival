package org.geppi.main;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.geppi.main.commands.KillstreakCMD;
import org.geppi.main.commands.MetaSurvivalCMD;
import org.geppi.main.commands.ReferCMD;
import org.geppi.main.handlers.BadwordsHandler;
import org.geppi.main.handlers.ColorHandler;
import org.geppi.main.handlers.PlayerHandler;
import org.geppi.main.listeners.ChatEvent;
import org.geppi.main.listeners.JoinEvent;
import org.geppi.main.listeners.KillstreakEvent;
import org.geppi.main.listeners.LeaveEvent;

public class Main extends JavaPlugin {

    private static ColorHandler colorHandler;
    private static Main instance;
    private BadwordsHandler handler;
    private static PlayerHandler playerHandler;



    @Override
    public void onEnable() {
        instance = this;
        this.handler = new BadwordsHandler(this);

        this.getCommand("metasurvival").setExecutor(new MetaSurvivalCMD());
        this.getCommand("killstreak").setExecutor(new KillstreakCMD());


        Bukkit.getPluginManager().registerEvents(new ChatEvent(),this);
        Bukkit.getPluginManager().registerEvents(new KillstreakEvent(),this);
        Bukkit.getPluginManager().registerEvents(new ReferCMD(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LeaveEvent(), this);


    }

    @Override
    public void onDisable() {


    }

    public static Main getInstance() {
        return instance;
    }

    public BadwordsHandler getHandler() {
        return this.handler;
    }

    public static ColorHandler getColorHandler() {
        return colorHandler;
    }

    public void setHandler(BadwordsHandler handler) {
        this.handler = handler;
    }

}
