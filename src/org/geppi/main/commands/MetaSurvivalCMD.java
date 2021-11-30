package org.geppi.main.commands;

import com.sun.istack.internal.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.geppi.main.Main;
import org.geppi.main.handlers.BadwordsHandler;
import org.geppi.main.handlers.ColorHandler;
import org.geppi.main.handlers.PlayerHandler;

public class MetaSurvivalCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 0) {
            sender.sendMessage("not enough arguments");
            return true;
        }

        if(args[0].equalsIgnoreCase("reloadConfig")) {


            Main.getInstance().setHandler(new BadwordsHandler(Main.getInstance()));
            sender.sendMessage("Reloaded!!");
        }

     return true;


    }

}
