package com.thethreatgames.threatgames.events.infectionEffects;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class effectSix extends BukkitRunnable {

    private JavaPlugin plugin;
    private Player player;

    public effectSix(JavaPlugin plugin, Player player){
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {
        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 240, 1));

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(32);
    }
}
