package com.thethreatgames.threatgames.events;

import com.thethreatgames.threatgames.ConfigManager;
import com.thethreatgames.threatgames.Threatgames;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class RemoveHelmetEvent implements Listener {
    private final Threatgames plugin;
    public RemoveHelmetEvent(Threatgames plugin) { this.plugin = plugin; }


    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(ConfigManager.playerscfg.getBoolean("enabled") == true){
            Player player = (Player) event.getWhoClicked();
            if(ConfigManager.playerscfg.contains(player.getName())){
                Inventory clickedItem = event.getClickedInventory();
                int clickedSlot = event.getSlot();

                //player.sendMessage("You clicked on " + clickedSlot);

                if(clickedSlot == 39){
                    event.setCancelled(true);
                }
            }
        }
    }
}
