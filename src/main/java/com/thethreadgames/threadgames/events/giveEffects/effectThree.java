package com.thethreadgames.threadgames.events.giveEffects;

import org.bukkit.ChatColor;
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
        player.sendMessage(ChatColor.RED + "You have been infected! \n To cure yourself eat a Golden Apple!");
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 480, 1000));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 480, 24));

    }

    public BukkitRunnable getTask(){
        return this;
    }


}