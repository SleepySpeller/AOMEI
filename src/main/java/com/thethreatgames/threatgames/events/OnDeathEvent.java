package com.thethreatgames.threatgames.events;

import com.thethreatgames.threatgames.ConfigManager;
import com.thethreatgames.threatgames.Threatgames;
import com.thethreatgames.threatgames.events.infectionEffects.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

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

                player.sendMessage(ChatColor.RED + "[ThreatGames] You died!");
                ConfigManager.playerscfg.set(player.getName(), null);


                effectOne effectOne = new effectOne(plugin, player);
                effectTwo effect2 = new effectTwo(plugin, player);
                effectThree effect3 = new effectThree(plugin, player);
                effectFour effect4 = new effectFour(plugin, player);
                effectFive effect5 = new effectFive(plugin, player);
                effectSix effect6 = new effectSix(plugin, player);
                effectSeven effect7 = new effectSeven(plugin, player);
                playerFullyInfected infectedFunction = new playerFullyInfected(plugin, player);
                try {
                    effectOne.cancel();
                    effect2.cancel();
                    effect3.cancel();
                    effect4.cancel();
                    effect5.cancel();
                    effect6.cancel();
                    effect7.cancel();
                    infectedFunction.cancel();

                    int taskID1 = effectOne.getTaskId();
                    int taskID2 = effect2.getTaskId();
                    int taskID3 = effect3.getTaskId();
                    int taskID4 = effect4.getTaskId();
                    int taskID5 = effect5.getTaskId();
                    int taskID6 = effect6.getTaskId();
                    int taskID7 = effect7.getTaskId();
                    int taskIDinf = infectedFunction.getTaskId();

                    plugin.getServer().getScheduler().cancelTask(taskID1);
                    plugin.getServer().getScheduler().cancelTask(taskID2);
                    plugin.getServer().getScheduler().cancelTask(taskID3);
                    plugin.getServer().getScheduler().cancelTask(taskID4);
                    plugin.getServer().getScheduler().cancelTask(taskID5);
                    plugin.getServer().getScheduler().cancelTask(taskID6);
                    plugin.getServer().getScheduler().cancelTask(taskID7);
                    plugin.getServer().getScheduler().cancelTask(taskIDinf);
                } catch (IllegalStateException error) {
                    Bukkit.getServer().getConsoleSender().sendMessage("[AOMEI] Tasks should be cancelled");
                }
            }
        }
    }
}
