package com.thethreatgames.threatgames.events;

import com.thethreatgames.threatgames.ConfigManager;
import com.thethreatgames.threatgames.Threatgames;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class ZombieTargetInfectedEvent implements Listener {
    private final Threatgames plugin;
    public ZombieTargetInfectedEvent(Threatgames plugin) { this.plugin = plugin; }

    @EventHandler
    public void ZombieTargetsInfectedEvent(EntityTargetEvent event){
        Entity target = event.getTarget();
        Entity attacker = event.getEntity(); //jbg

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
