package com.thethreatgames.threatgames.commands;

import com.thethreatgames.threatgames.ConfigManager;
import com.thethreatgames.threatgames.Threatgames;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartPluginCommand implements CommandExecutor {
    private final Threatgames main;

    public StartPluginCommand(Threatgames main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = ((Player) sender).getPlayer();

        if(!sender.hasPermission("aomei.toggle") || !sender.isOp()){
            sender.sendMessage(ChatColor.RED + "You don't have the permission to execute this command");
            return true;
        }
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
