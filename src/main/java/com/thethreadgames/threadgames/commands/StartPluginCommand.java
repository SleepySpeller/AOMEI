package com.thethreadgames.threadgames.commands;

import com.thethreadgames.threadgames.ConfigManager;
import com.thethreadgames.threadgames.Threadgames;
import jdk.javadoc.internal.tool.Start;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartPluginCommand implements CommandExecutor {
    private final Threadgames main;

    public StartPluginCommand(Threadgames main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = ((Player) sender).getPlayer();

        if(ConfigManager.playerscfg.getBoolean("enabled") == false){
            ConfigManager.playerscfg.set("enabled", true);
            ConfigManager.savePlayers();
            ConfigManager.reloadPlayers();
            player.sendMessage(ChatColor.GREEN + "The AOMEI Plugin has been enabled!");
            return true;
        }else{
            ConfigManager.playerscfg.set("enabled", false);
            ConfigManager.savePlayers();
            ConfigManager.reloadPlayers();
            player.sendMessage(ChatColor.DARK_AQUA + "The AOMEI Plugin has been disabled!");
            return true;
        }



    }
}
