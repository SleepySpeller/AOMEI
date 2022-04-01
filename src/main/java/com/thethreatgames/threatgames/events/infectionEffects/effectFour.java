package com.thethreatgames.threatgames.events.infectionEffects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class effectFour extends BukkitRunnable {

    private JavaPlugin plugin;
    private Player player;

    public effectFour(JavaPlugin plugin, Player player){
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {
        player.sendMessage(ChatColor.RED + "You're in phase four, you better look for that golden apple!");
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 8400, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 2));
    }
}
