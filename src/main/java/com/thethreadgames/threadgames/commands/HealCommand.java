package com.thethreadgames.threadgames.commands;

import com.thethreadgames.threadgames.Threadgames;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    private final Threadgames main;

    public HealCommand(Threadgames main){
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = ((Player) sender).getPlayer();

            player.setHealth(20);
            player.setSaturation(20);

            player.sendMessage(ChatColor.GREEN + "[ThreadGames] " + ChatColor.DARK_AQUA + "You have been healed!");

            return true;
        }else{
            main.getLogger().info("You have to be a player to execute this command!");
            return true;
        }

    }
}
