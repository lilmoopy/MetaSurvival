package org.geppi.main.handlers;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PlayerHandler {

    Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    public void setupPlayer(Player p) {
        File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
        if (!f.exists())
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.addDefault("General.Name", p.getName());
        yml.addDefault("General.firstJoin", format.format(now));

        yml.addDefault("General.Referral", Integer.valueOf(0));
        yml.addDefault("General.hasReferred", Integer.valueOf(0));

        yml.addDefault("Killstreak.Kills", Integer.valueOf(0));
        yml.addDefault("Killstreak.Deaths", Integer.valueOf(0));
        yml.addDefault("Killstreak.killStreak", Integer.valueOf(0));
        yml.addDefault("Killstreak.LKB", "none");
        yml.addDefault("Killstreak.combatLog", "");







        yml.options().copyDefaults(true);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public int getCombatLogTime(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Killstreak.combatLog");
    }

    public boolean setCombatLogTime(Player player, int num) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Killstreak.combatLog", num);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public int getKills(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Killstreak.Kills");
    }

    public int getDeaths(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Killstreak.Deaths");
    }

    public int getKillStreak(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("Killstreak.killStreak");
    }

    public boolean resetKillStreak(Player player, int rank) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Killstreak.killStreak", Integer.valueOf(rank));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addKill(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getKills(player);
        currentMoney += amount;
        yml.set("Killstreak.Kills", Integer.valueOf(currentMoney));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addDeath(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getDeaths(player);
        currentMoney += amount;
        yml.set("Killstreak.Deaths", Integer.valueOf(currentMoney));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addToKillStreak(Player player, int amount) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        int currentMoney = getKillStreak(player);
        currentMoney += amount;
        yml.set("Killstreak.killStreak", Integer.valueOf(currentMoney));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setLastKilledByPlayer(Player p, String name) {
        File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("Killstreak.LKB", name);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getLKBP(Player p) {
        File f = new File("plugins/GameCore/PlayerData/" + p.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getString("Killstreak.LKB");
    }

    public int getReferrals(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.Referral");
    }

    public boolean setReferrals(Player player, int refer) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.Referral", Integer.valueOf(refer));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getHasReferred(Player player) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt("General.hasReferred");
    }

    public boolean setHasReferred(Player player, int refer) {
        File f = new File("plugins/GameCore/PlayerData/" + player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set("General.hasReferred", Integer.valueOf(refer));
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}