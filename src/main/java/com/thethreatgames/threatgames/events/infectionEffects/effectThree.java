package com.thethreatgames.threatgames.events.infectionEffects;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class effectThree extends BukkitRunnable {

    private JavaPlugin plugin;

    public effectThree(JavaPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    private Player player;

    @Override
    public void run() {
        //player.sendMessage(ChatColor.RED + "You have been infected! \n To cure yourself eat a Golden Apple!");
        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 8400, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 24));

    }

    public BukkitRunnable getTask(){
        return this;
    }


}