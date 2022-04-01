package com.thethreatgames.threatgames.events;

import com.thethreatgames.threatgames.ConfigManager;
import com.thethreatgames.threatgames.Threatgames;
import com.thethreatgames.threatgames.events.infectionEffects.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
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


    //the function for the player curing when eating the golden apple,
    @EventHandler
    public void onEating(PlayerItemConsumeEvent event){

        //gets the instance of the player
        Player player = event.getPlayer();

        //if the plugin is enabled
        if(ConfigManager.playerscfg.getBoolean("enabled") == true){

            //if the player is infected
            if(ConfigManager.playerscfg.contains(player.getName())){

                //gets the item that the player is consuming
                ItemStack itemEating = event.getItem();
                Material itemType = itemEating.getType();

                //if the player is eating an golden apple
                if(itemType == Material.GOLDEN_APPLE) {

                    //cancels its current tasks
                    getServer().getScheduler().cancelTasks(plugin);

                    //removes the effects
                    player.removePotionEffect(PotionEffectType.SLOW);
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                    player.removePotionEffect(PotionEffectType.HUNGER);
                    player.removePotionEffect(PotionEffectType.CONFUSION);
                    player.removePotionEffect(PotionEffectType.REGENERATION);
                    player.removePotionEffect(PotionEffectType.WEAKNESS);
                    player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
                    player.removePotionEffect(PotionEffectType.POISON);
                    player.removePotionEffect(PotionEffectType.WITHER);
                    player.removePotionEffect(PotionEffectType.REGENERATION);

                    //sets the players max hearts back to default
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);

                    //informs the player that they are cured
                    player.sendMessage(ChatColor.GOLD + "[AOMEI] You have been cured");


                      //an old algorithm of cancelling tasks, i kept it here just in case i need it
//                    effectOne effectOne = new effectOne(plugin, player);
//                    effectTwo effect2 = new effectTwo(plugin, player);
//                    effectThree effect3 = new effectThree(plugin, player);
//                    effectFour effect4 = new effectFour(plugin, player);
//                    effectFive effect5 = new effectFive(plugin, player);
//                    effectSix effect6 = new effectSix(plugin, player);
//                    effectSeven effect7 = new effectSeven(plugin, player);
//                    playerFullyInfected infectedFunction = new playerFullyInfected(plugin, player);
//                    try {
//                        effectOne.cancel();
//                        effect2.cancel();
//                        effect3.cancel();
//                        effect4.cancel();
//                        effect5.cancel();
//                        effect6.cancel();
//                        effect7.cancel();
//                        infectedFunction.cancel();
//
//                        int taskID1 = effectOne.getTaskId();
//                        int taskID2 = effect2.getTaskId();
//                        int taskID3 = effect3.getTaskId();
//                        int taskID4 = effect4.getTaskId();
//                        int taskID5 = effect5.getTaskId();
//                        int taskID6 = effect6.getTaskId();
//                        int taskID7 = effect7.getTaskId();
//                        int taskIDinf = infectedFunction.getTaskId();
//
//                        plugin.getServer().getScheduler().cancelTask(taskID1);
//                        plugin.getServer().getScheduler().cancelTask(taskID2);
//                        plugin.getServer().getScheduler().cancelTask(taskID3);
//                        plugin.getServer().getScheduler().cancelTask(taskID4);
//                        plugin.getServer().getScheduler().cancelTask(taskID5);
//                        plugin.getServer().getScheduler().cancelTask(taskID6);
//                        plugin.getServer().getScheduler().cancelTask(taskID7);
//                        plugin.getServer().getScheduler().cancelTask(taskIDinf);
//                    } catch (IllegalStateException error) {
//                        Bukkit.getServer().getConsoleSender().sendMessage("[AOMEI] Cancelling tasks!");
//                    }

                    //gives the player its helmet back
                    ItemStack headItem = ConfigManager.playerscfg.getItemStack(player.getName() + ".HeadItem");
                    player.getInventory().setHelmet(headItem);

                    //removes the player from the infected list
                    ConfigManager.playerscfg.set(player.getName(), null);
                    ConfigManager.savePlayers();
                    ConfigManager.reloadPlayers();
                }
            }
        }
    }
}
