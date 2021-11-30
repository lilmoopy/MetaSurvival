package org.geppi.main.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geppi.main.utils.Bar;

public class JoinEvent implements Listener {

    private Bar bar;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        bar = new Bar();
        bar.createBar();

        bar.addPlayer(event.getPlayer());

    }

}

