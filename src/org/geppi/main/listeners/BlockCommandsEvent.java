package org.geppi.main.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.geppi.main.handlers.ColorHandler;

public class BlockCommandsEvent {

    ColorHandler colorHandler = new ColorHandler();

    @EventHandler
    public void hatCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();
        String[] args = message.split(" ");

        Player player = event.getPlayer();

        if(event.getPlayer().hasPermission("metasurvival.staff")) {
            if (event.getMessage().startsWith("/") && event.getMessage().contains(":")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(colorHandler.error + "You can't put : inside of your commands without permission!");
            }
        }

    }
}