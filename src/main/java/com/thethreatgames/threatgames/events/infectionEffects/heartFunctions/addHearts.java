package com.thethreatgames.threatgames.events.infectionEffects.heartFunctions;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class addHearts{
    public static void instantHearts(Player player){

        //Gets the attribute var. that we will need
        AttributeInstance playerHearts = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        //Get the max player health
        double hearts = playerHearts.getValue();

        if(hearts < 40){
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hearts+2);
        }else{
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
        }
    }
}
