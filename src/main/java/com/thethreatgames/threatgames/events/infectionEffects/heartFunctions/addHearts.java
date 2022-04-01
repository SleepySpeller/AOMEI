package com.thethreatgames.threatgames.events.infectionEffects.heartFunctions;

import com.thethreatgames.threatgames.ConfigManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class addHearts{
    public static void instantHearts(Player player) {
        if (ConfigManager.playerscfg.getBoolean("enabled", true)) {
            if (ConfigManager.playerscfg.contains(player.getName())) {
                //Gets the attribute var. that we will need
                AttributeInstance playerHearts = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);

                //Get the max player health
                double hearts = playerHearts.getValue();

                if (hearts < 40) {
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hearts + 2);
                } else {
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
                }
            }
        }
    }
}
