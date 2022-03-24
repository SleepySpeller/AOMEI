package com.thethreatgames.threatgames.events.giveEffects;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class playerDeath extends BukkitRunnable {

    private JavaPlugin plugin;

    private Player player;

    public playerDeath(JavaPlugin plugin, Player player){
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 250));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 480, 1000));
    }
}
