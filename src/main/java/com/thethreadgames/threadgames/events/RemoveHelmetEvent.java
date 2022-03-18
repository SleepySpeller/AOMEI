package com.thethreadgames.threadgames.events;

import com.thethreadgames.threadgames.ConfigManager;
import com.thethreadgames.threadgames.Threadgames;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class RemoveHelmetEvent implements Listener {
    private final Threadgames plugin;
    public RemoveHelmetEvent(Threadgames plugin) { this.plugin = plugin; }


    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        Inventory clickedItem = event.getClickedInventory();

        if(ConfigManager.playerscfg.getBoolean("enabled") == true){
            if(ConfigManager.playerscfg.contains(player.getName())){
                if(event.getSlotType() == InventoryType.SlotType.ARMOR){
                    event.setCancelled(true);
                }
            }
        }
    }
}
