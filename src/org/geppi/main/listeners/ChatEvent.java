package org.geppi.main.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.geppi.main.Main;

import java.util.*;
import java.util.stream.Collectors;


public class ChatEvent implements Listener  {

    public String format(Player player, String msg) {
        return ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, msg));
    }

    public String formatDefault(Player player, String msg) {
        return PlaceholderAPI.setPlaceholders(player, msg);
    }

    private Map<UUID, Long> cooldowns = new HashMap<>();
    private Map<UUID, String> lastMessage = new HashMap<>();

    private String staffPrefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "StaffChat" + ChatColor.GRAY + "] " + ChatColor.WHITE;


    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void chat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
         List<String> badWords = Main.getInstance().getHandler().getBadWords().stream().map(String::toLowerCase).collect(Collectors.toList());


            if((player.hasPermission("metasurvival.staff") || player.isOp()) && message.startsWith("@")) {
                event.setCancelled(true);
                for(Player online : Bukkit.getOnlinePlayers()) {
                    if(online.hasPermission("metasurvival.staff")) {
                        online.sendMessage(staffPrefix + player.getName() + ChatColor.GRAY + ": " + ChatColor.YELLOW  + event.getMessage().replaceFirst("@", ""));
                    }
                }
        }


        if(!player.hasPermission("metasurvival.chatbypass")) {
            long currentTime = System.currentTimeMillis();
            long lastUse = this.cooldowns.getOrDefault(player.getUniqueId(), 0L);

            if (Arrays.stream(message.toLowerCase().split(" ")).filter(badWords::contains).count() > 0L) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You just said a filtered word! no bad!");
                return;
            }

            String prevMessage = this.lastMessage.get(player.getUniqueId());
            if (prevMessage != null && prevMessage.equalsIgnoreCase(message)) {
                player.sendMessage(ChatColor.RED +"Please do not repeat the same message over and over!!");
                event.setCancelled(true);
                return;
            }


            if (lastUse + 1000 > currentTime) {
                player.sendMessage(ChatColor.RED + "Don't Spam!");
                event.setCancelled(true);
                return;
            }






        }




        this.cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
        this.lastMessage.put(player.getUniqueId(), message);


        if(player.hasPermission("metasurvival.staff")) {
            event.setFormat(format(event.getPlayer(), "%vault_prefix% %player_displayname% %eternaltags_tag%: &9" + message));
        } else {
            event.setFormat(formatDefault(event.getPlayer(), "%vault_prefix% %player_displayname% %eternaltags_tag%: &7" + message));
        }
    }

}
