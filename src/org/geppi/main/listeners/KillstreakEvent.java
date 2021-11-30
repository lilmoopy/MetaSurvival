package org.geppi.main.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.geppi.main.Main;
import org.geppi.main.handlers.PlayerHandler;

public class KillstreakEvent implements Listener {

    PlayerHandler playerHandler = new PlayerHandler();

    @EventHandler
    public void onKIll(PlayerDeathEvent event) {
        event.setDeathMessage(null);

        if (!(event.getEntity().getKiller() instanceof Player)) {
            Player killed = event.getEntity();
            playerHandler.addDeath(killed, 1);
            playerHandler.resetKillStreak(killed, 0);
            return;
        } else if (event.getEntity() instanceof Player && event.getEntity().getKiller() instanceof Player) {
            Player killer = event.getEntity().getKiller();
            Player killed = event.getEntity();
            playerHandler.addToKillStreak(killer, 1);
            playerHandler.resetKillStreak(killed, 0);
            playerHandler.addKill(killer, 1);
            playerHandler.addDeath(killed, 1);
            playerHandler.setLastKilledByPlayer(killed.getPlayer(), killer.getName());

            if (playerHandler.getKillStreak(killer) <= 30 && playerHandler.getKillStreak(killer) % 5 == 0) {
                Bukkit.getServer().broadcastMessage(killer.getName()
                        + Main.getColorHandler().message + " has reached a kill streak of " + playerHandler.getKillStreak(killer));
            }

        }
    }
}