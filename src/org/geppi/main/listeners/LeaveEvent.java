package org.geppi.main.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.geppi.main.utils.Bar;

public class LeaveEvent implements Listener {

    private Bar bar;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        bar.getBar().removePlayer(event.getPlayer());

    }

}
