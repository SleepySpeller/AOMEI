package com.thethreatgames.threatgames.events;

import com.thethreatgames.threatgames.ConfigManager;
import com.thethreatgames.threatgames.Threatgames;
import com.thethreatgames.threatgames.events.giveEffects.effectOne;
import com.thethreatgames.threatgames.events.giveEffects.effectThree;
import com.thethreatgames.threatgames.events.giveEffects.effectTwo;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

import static org.bukkit.Bukkit.getPlayer;


public class PlayerHitEvent implements Listener {

    private final Threatgames plugin;
    public PlayerHitEvent(Threatgames plugin) { this.plugin = plugin; }
    static Threatgames mainClass;



    public void startDelays(Player player){
        player.sendMessage(ChatColor.RED + "You have been infected! \n To cure yourself eat an golden apple!");
        player.sendMessage(ChatColor.YELLOW + "Overtime, you will get weird effects, if you don't cure yourself you will die!");

        ConfigManager.saveInfectedPlayer(player);

        player.getInventory().setHelmet(new ItemStack(Material.CARVED_PUMPKIN, 1));

        effectOne effect = new effectOne(plugin, player);
        effect.runTaskLater(plugin, 1200L);

        effectTwo effect2 = new effectTwo(plugin, player);
        effect2.runTaskLater(plugin, 2400L);

        effectThree effect3 = new effectThree(plugin, player);
        effect3.runTaskLater(plugin, 3600L);

    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event){

        Entity isHitting = event.getDamager();
        Entity gotHit = event.getEntity();


        EntityType gotHitType = gotHit.getType();
        EntityType isHittingType = isHitting.getType();

        if(ConfigManager.playerscfg.getBoolean("enabled") == true){
            if(gotHitType == EntityType.PLAYER){
                if(isHittingType == EntityType.ZOMBIE){
                    String namePlayer = gotHit.getName();
                    Player player = getPlayer(namePlayer);

                    if(ConfigManager.playerscfg.contains(player.getName()) == false){
                        Random rand = new Random(); //instance of random class
                        int upperbound = 100;
                        int int_random = rand.nextInt(upperbound);

                        double health = player.getHealth();

                        if (health < 10){
                            startDelays(player);


                        }else{

                            if (int_random < 5){
                                startDelays(player);
                            }else{
                                player.sendMessage(ChatColor.RED + "You have been hit by a zombie!");
                            }
                        }
                    }
                }else if(isHittingType == EntityType.PLAYER){
                    String namePlayerHit = gotHit.getName();
                    Player playerHit = getPlayer(namePlayerHit);

                    String namePlayerHitting = isHitting.getName();
                    Player playerHitting = getPlayer(namePlayerHitting);

                    if(ConfigManager.playerscfg.contains(playerHitting.getName())) {
                        if (!ConfigManager.playerscfg.contains(playerHit.getName())) {
                            Random rand = new Random(); //instance of random class
                            int upperbound = 100;
                            int int_random = rand.nextInt(upperbound);

                            double health = playerHit.getHealth();

                            if (health < 10) {
                                startDelays(playerHit);


                            } else {

                                if (int_random < 5) {
                                    startDelays(playerHit);
                                } else {
                                    playerHit.sendMessage(ChatColor.RED + "You have been hit by a zombie!");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

