package com.thethreatgames.threatgames.events;

import com.thethreatgames.threatgames.ConfigManager;
import com.thethreatgames.threatgames.Threatgames;
import com.thethreatgames.threatgames.events.giveEffects.effectOne;
import com.thethreatgames.threatgames.events.giveEffects.effectThree;
import com.thethreatgames.threatgames.events.giveEffects.effectTwo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

import static org.bukkit.Bukkit.getServer;

public class OnDeathEvent implements Listener {
    private final Threatgames plugin;
    public OnDeathEvent(Threatgames plugin) { this.plugin = plugin; }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if(ConfigManager.playerscfg.getBoolean("enabled") == true){
            Player player = event.getEntity().getPlayer();
            if(ConfigManager.playerscfg.contains(player.getName())){
                getServer().getScheduler().cancelTasks(plugin);

                player.removePotionEffect(PotionEffectType.SLOW);
                player.removePotionEffect(PotionEffectType.BLINDNESS);
                player.removePotionEffect(PotionEffectType.HUNGER);
                player.removePotionEffect(PotionEffectType.CONFUSION);

                player.sendMessage(ChatColor.RED + "[ThreadGames] You died!");
                ConfigManager.playerscfg.set(player.getName(), null);


                effectOne effectOne = new effectOne(plugin, player);
                effectTwo effect2 = new effectTwo(plugin, player);
                effectThree effect3 = new effectThree(plugin, player);
                try {
                    int taskID1 = effectOne.getTaskId();
                    int taskID2 = effect2.getTaskId();
                    int taskID3 = effect3.getTaskId();
                    effectOne.cancel();
                    effect2.cancel();
                    effect3.cancel();

                    plugin.getServer().getScheduler().cancelTask(taskID1);
                    plugin.getServer().getScheduler().cancelTask(taskID2);
                    plugin.getServer().getScheduler().cancelTask(taskID3);

                } catch (IllegalStateException error) {
                    Bukkit.getServer().getConsoleSender().sendMessage("[AOMEI] Tasks should be cancelled");
                }
            }
        }
    }
}
