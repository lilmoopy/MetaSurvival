package org.geppi.main.commands;

import com.sun.istack.internal.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.geppi.main.handlers.ColorHandler;
import org.geppi.main.handlers.PlayerHandler;

public class KillstreakCMD implements CommandExecutor {
    PlayerHandler playerHandler = new PlayerHandler();
    ColorHandler colorHandler  = new ColorHandler();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if(!(sender instanceof Player)){
            sender.sendMessage("Players only fool!");
            return true;
        }

        int kills = playerHandler.getKills(player);
        double deaths = playerHandler.getDeaths(player);
        double ratio = kills / deaths;

        player.sendMessage(colorHandler.main + "User: " + colorHandler.message + player.getName());
        player.sendMessage(colorHandler.main + "Kill Streak: " + colorHandler.message + playerHandler.getKillStreak(player));
        player.sendMessage(colorHandler.main + "Kills: " + colorHandler.message + playerHandler.getKills(player));
        player.sendMessage(colorHandler.main + "Deaths: " + colorHandler.message + playerHandler.getDeaths(player));
        player.sendMessage(colorHandler.main + "Last Killed By: " + colorHandler.message + playerHandler.getLKBP(player));
        player.sendMessage(colorHandler.main + "K/D Ratio: " + colorHandler.message + ratio);
        return true;
    }

}
