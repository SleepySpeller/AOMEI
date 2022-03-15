package com.thethreadgames.threadgames.commands;

import com.thethreadgames.threadgames.Threadgames;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ClearCommand implements CommandExecutor {
    private final Threadgames main;

    public ClearCommand(Threadgames main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;

            if(player.isOp() || player.hasPermission("clearinventory")){
                Inventory inv = player.getInventory();
                inv.clear();

                player.sendMessage(ChatColor.GREEN + "Your inventory has been cleared!");
                return true;

            }else{
                player.sendMessage(ChatColor.RED + "You dont have the permission to use this command!");
                return true;
            }


        } else{
            main.getLogger().info("You have to be a player to clear your inventory");
            return true;
        }
    }
}
