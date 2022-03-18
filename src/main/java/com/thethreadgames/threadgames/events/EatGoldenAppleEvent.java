package com.thethreadgames.threadgames.events;

import com.thethreadgames.threadgames.ConfigManager;
import com.thethreadgames.threadgames.Threadgames;
import com.thethreadgames.threadgames.events.giveEffects.effectOne;
import com.thethreadgames.threadgames.events.giveEffects.effectThree;
import com.thethreadgames.threadgames.events.giveEffects.effectTwo;
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
    private final Threadgames plugin;
    public EatGoldenAppleEvent(Threadgames plugin) { this.plugin = plugin; }

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
                        Bukkit.getServer().getConsoleSender().sendMessage("[AOMEI] Cancelling tasks!");
                    }
                    ItemStack headItem = ConfigManager.playerscfg.getItemStack(player.getName() + ".HeadItem");

                    player.getInventory().setHelmet(ConfigManager.playerscfg.getItemStack(player.getName() + ".HeadItem"));


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
