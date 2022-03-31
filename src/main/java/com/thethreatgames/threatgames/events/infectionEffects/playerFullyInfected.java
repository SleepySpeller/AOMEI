package com.thethreatgames.threatgames.events.infectionEffects;

import com.thethreatgames.threatgames.events.infectionEffects.heartFunctions.takeawayHearts;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class playerFullyInfected extends BukkitRunnable {

    private JavaPlugin plugin;

    private Player player;

    public playerFullyInfected(JavaPlugin plugin, Player player){
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 250));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1000));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 90, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, Integer.MAX_VALUE, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 1));


        player.sendMessage(ChatColor.RED + "[AOMEI] You have been fully transformed to a zombie!");

        takeawayHearts hearts = new takeawayHearts(plugin, player);
        hearts.runTaskLater(plugin, 1100L);
    }
}
