package com.thethreatgames.threatgames.events.infectionEffects.heartFunctions;

import com.thethreatgames.threatgames.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class rescheduleTakeawayHearts {
    public static void reschedule(JavaPlugin plugin, Player player){
        if(ConfigManager.playerscfg.getBoolean("enabled", true)){
            if(ConfigManager.playerscfg.contains(player.getName())){
                takeawayHearts takeHearts = new takeawayHearts(plugin, player);
                takeHearts.runTaskLater(plugin, 1100L);
            }
        }

    }
}