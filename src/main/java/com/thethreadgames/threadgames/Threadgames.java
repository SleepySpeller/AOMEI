package com.thethreadgames.threadgames;

import com.thethreadgames.threadgames.commands.StartPluginCommand;
import com.thethreadgames.threadgames.events.EatGoldenAppleEvent;
import com.thethreadgames.threadgames.events.OnDeathEvent;
import com.thethreadgames.threadgames.events.PlayerHitEvent;
import com.thethreadgames.threadgames.events.ZombieTargetInfectedEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Threadgames extends JavaPlugin {
    private ConfigManager cfgn;
    public String infectedPlayers[];
//YES COMMENTS GO BRRR
    @Override
    public void onEnable() {
        loadConfigManager();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ThreadPlugin] ThreadPlugin is enabled!");
        getServer().getPluginManager().registerEvents(new PlayerHitEvent(this), this);
        getServer().getPluginManager().registerEvents(new EatGoldenAppleEvent(this), this);
        getServer().getPluginManager().registerEvents(new ZombieTargetInfectedEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnDeathEvent(this), this);


        getServer().getPluginCommand("AOMEI").setExecutor(new StartPluginCommand(this));

    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[ThreadPlugin] ThreadPlugin is being disabled...");
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
