package com.thethreatgames.threatgames.events.infectionEffects;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class effectOne extends BukkitRunnable { //so?

    private JavaPlugin plugin;

    public effectOne(JavaPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    private Player player;

    @Override
    public void run() {
        player.sendMessage(ChatColor.RED + "You are at stage one of being infected! \n Over time you gain diffrent effects! \n You also have to hit other players, otherwise you will lose hearts, but won't die");
        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 8400, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 8400, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8400, 2));

    }

    public BukkitRunnable getTask(){
        return this;
    }
}