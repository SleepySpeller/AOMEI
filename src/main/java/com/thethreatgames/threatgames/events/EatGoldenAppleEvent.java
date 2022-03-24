package com.thethreatgames.threatgames.events;

import com.thethreatgames.threatgames.ConfigManager;
import com.thethreatgames.threatgames.Threatgames;
import com.thethreatgames.threatgames.events.giveEffects.effectOne;
import com.thethreatgames.threatgames.events.giveEffects.effectThree;
import com.thethreatgames.threatgames.events.giveEffects.effectTwo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import static org.bukkit.Bukkit.getServer;

public class EatGoldenAppleEvent implements Listener{
    private final Threatgames plugin;
    public EatGoldenAppleEvent(Threatgames plugin) { this.plugin = plugin; }

    @EventHandler
    public void onEating(PlayerItemConsumeEvent event){

        Player player = event.getPlayer();
        if(ConfigManager.playerscfg.getBoolean("enabled") == true){
            if(ConfigManager.playerscfg.contains(player.getName())){
                ItemStack itemEating = event.getItem();
                Material itemType = itemEating.getType();

                if(itemType == Material.GOLDEN_APPLE) {
                    getServer().getScheduler().cancelTasks(plugin);

                    player.removePotionEffect(PotionEffectType.SLOW);
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                    player.removePotionEffect(PotionEffectType.HUNGER);
                    player.removePotionEffect(PotionEffectType.CONFUSION);

                    player.sendMessage(ChatColor.GOLD + "[ThreadGames] You have been cured");


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
                        Bukkit.getServer().getConsoleSender().sendMessage("[AOMEI] Cancelling tasks!");
                    }
                    ItemStack headItem = ConfigManager.playerscfg.getItemStack(player.getName() + ".HeadItem");

                    if(headItem != null){
                        player.getInventory().setHelmet(headItem);
                    }

                    ConfigManager.playerscfg.set(player.getName(), null);
                    ConfigManager.savePlayers();
                    ConfigManager.reloadPlayers();

//                    player.sendMessage("Attempting to get item ...");
//                    ItemStack item = ConfigManager.playerscfg.getItemStack(player.getName() + ".HeadItem");
//                    if (item == null) {
//                        player.sendMessage("Item is null, check that " + player.getName() + ".HeadItem config path exists");
//                        return;
//                    }
//
//                    player.sendMessage("Read item: " + item.getType().name());

                }
            }
        }
    }
}
