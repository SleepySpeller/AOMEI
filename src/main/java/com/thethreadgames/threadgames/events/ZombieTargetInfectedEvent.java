package com.thethreadgames.threadgames.events;

import com.thethreadgames.threadgames.ConfigManager;
import com.thethreadgames.threadgames.Threadgames;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class ZombieTargetInfectedEvent implements Listener {
    private final Threadgames plugin;
    public ZombieTargetInfectedEvent(Threadgames plugin) { this.plugin = plugin; }

    @EventHandler
    public void ZombieTargetsInfectedEvent(EntityTargetEvent event){
        Entity target = event.getTarget();
        Entity attacker = event.getEntity();

        if(attacker instanceof Zombie){
            if (target instanceof Player){
                Player player = ((Player) target).getPlayer();
                if (ConfigManager.playerscfg.contains(target.getName())){
                    event.setCancelled(true);
                }
            }

        }


    }
}
