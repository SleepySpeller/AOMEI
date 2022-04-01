package com.thethreatgames.threatgames;

import com.thethreatgames.threatgames.commands.StartPluginCommand;
import com.thethreatgames.threatgames.events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Threatgames extends JavaPlugin {
    private ConfigManager cfgn;
    public String infectedPlayers[];
//YES COMMENTS GO BRRR
    @Override
    public void onEnable() {
        loadConfigManager();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[AOMEI] AOMEI is enabled!");

        getServer().getPluginManager().registerEvents(new PlayerHitEvent(this), this);
        getServer().getPluginManager().registerEvents(new EatGoldenAppleEvent(this), this);
        getServer().getPluginManager().registerEvents(new ZombieTargetInfectedEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnDeathEvent(this), this);
        getServer().getPluginManager().registerEvents(new RemoveHelmetEvent(this), this);


        getServer().getPluginCommand("AOMEI").setExecutor(new StartPluginCommand(this));
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[AOMEI] AOMEI is being disabled...");
    }

    public void loadConfigManager(){
        cfgn = new ConfigManager();
        cfgn.setup();
        cfgn.savePlayers();
        cfgn.reloadPlayers();
    }

    public void createToggleConfig(){
        ConfigManager.playerscfg.set("enabled", true);
        ConfigManager.savePlayers();
        ConfigManager.reloadPlayers();
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();

    }
}
