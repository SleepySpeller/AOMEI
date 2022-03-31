package com.thethreatgames.threatgames.events.infectionEffects;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class effectTwo extends BukkitRunnable {

    private JavaPlugin plugin;

    public effectTwo(JavaPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    private Player player;

    @Override
    public void run() {
        //player.sendMessage(ChatColor.RED + "You have been infected! \n To cure yourself eat a Golden Apple!");
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 120, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 140, 1000));

    }

    public BukkitRunnable getTask(){
        return this;
    }


}