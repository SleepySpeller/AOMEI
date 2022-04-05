package com.thethreatgames.threatgames;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ConfigManager {
    private Threatgames plugin = Threatgames.getPlugin(Threatgames.class);

    public static FileConfiguration playerscfg;
    public static File playersfile;



    public void setup(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        playersfile = new File(plugin.getDataFolder(), "players.yml");

        if(!playersfile.exists()){
            try{
                playersfile.createNewFile();
            }catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[AOMEI] Could not create the players.yml file");
            }
        }
        playerscfg = YamlConfiguration.loadConfiguration(playersfile);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[AOMEI] players.yml file has been created and loaded successfully");

    }

    public FileConfiguration getPlayers(){
        return playerscfg;
    }

    public static void savePlayers(){
        try{
            playerscfg.save(playersfile);
        }catch(IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[AOMEI] Could not create the players.yml file");
        }
    }

    public static void reloadPlayers(){
        playerscfg = YamlConfiguration.loadConfiguration(playersfile);
    }


    public static void saveInfectedPlayer(Player player){
        Bukkit.getServer().getConsoleSender().sendMessage("[ThreadGames] Trying to save the infected player!");
        String playerName = player.getName();

        ItemStack headItem = player.getInventory().getHelmet();

        //ItemStack stack = new ItemStack(headItem.getType());

        if(headItem == null){
            playerscfg.set(playerName + ".HeadItem", "none");
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[AOMEI] The player isn't wearing an hat");
        }else{
            playerscfg.set(playerName + ".HeadItem", headItem);
        }


        savePlayers();
        reloadPlayers();
    }
}
