package com.thethreatgames.threatgames.events.infectionEffects.heartFunctions;

import com.thethreatgames.threatgames.ConfigManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class takeawayHearts extends BukkitRunnable {

    private JavaPlugin plugin;
    private Player player;

    public takeawayHearts(JavaPlugin plugin, Player player){
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {
        if(ConfigManager.playerscfg.getBoolean("enabled", true)){
            if(ConfigManager.playerscfg.contains(player.getName())){
                //Gets the attribute var. that we will need
                AttributeInstance playerHearts = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);

                //Get the max player health
                double hearts = playerHearts.getValue();

                //Set it to be 1 heart less (2hp less)
                if(hearts > 2){
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hearts-2);

                    rescheduleTakeawayHearts.reschedule(plugin, player);
                }
            }
        }
    }

    public BukkitRunnable getTask(){
        return this;
    }
}