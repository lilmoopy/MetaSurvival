package org.geppi.main.utils;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import org.geppi.main.Main;

public class Bar {

    private int taskID = -1;
    private BossBar bar;

    public void addPlayer(Player player) {
        bar.addPlayer(player);
    }

    public BossBar getBar() {
        return bar;
    }

    public void createBar() {
        bar = Bukkit.createBossBar(format("&c&lWelcome Back &4&l<3?"), BarColor.RED, BarStyle.SOLID);
        bar.setVisible(true);
        cast();
    }

    public void cast() {
        setTaskID(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            int count = -1;
            double progress = 1.0;
            final double time = 1.0/30;

            @Override
            public void run() {
                bar.setProgress(progress);

                switch(count) {
                    case -1:
                        break;
                    case 0:
                        bar.setColor(BarColor.PINK);
                        bar.setTitle(format("&4&lThere are no current sales! Sorry!"));
                        break;
                    case 1:
                        bar.setColor(BarColor.BLUE);
                        bar.setTitle(format("&c&lServers in development! Sorry!"));
                        break;
                    case 2:
                        bar.setColor(BarColor.GREEN);
                        bar.setTitle(format("&4&lgeppi thinks ur cute ;)!"));
                        break;
                    case 3:
                    default:
                        bar.setColor(BarColor.RED);
                        bar.setTitle(format("&c&lDon't forget to leave suggestions!!"));
                        count = -1;
                        break;
                }

                progress = progress - time;
                if (progress <= 0) {
                    count++;
                    progress = 1.0;
                }

            }

        }, 0, 20));
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

}
