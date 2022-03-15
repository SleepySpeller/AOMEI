package com.thethreadgames.threadgames.events;

import com.thethreadgames.threadgames.ConfigManager;
import com.thethreadgames.threadgames.Threadgames;
import com.thethreadgames.threadgames.events.giveEffects.effectOne;
import com.thethreadgames.threadgames.events.giveEffects.effectThree;
import com.thethreadgames.threadgames.events.giveEffects.effectTwo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;


public class PlayerHitEvent implements Listener {

    private final Threadgames plugin;
    public PlayerHitEvent(Threadgames plugin) { this.plugin = plugin; }
    static Threadgames mainClass;



    public void startDelays(Player player){
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "The player has been infected!");
        player.sendMessage(ChatColor.RED + "You have been infected!");

        ConfigManager.saveInfectedPlayer(player);

        player.getInventory().setHelmet(new ItemStack(Material.CARVED_PUMPKIN, 1));

        effectOne effect = new effectOne(plugin, player);
        effect.runTaskLater(plugin, 100L);

        effectTwo effect2 = new effectTwo(plugin, player);
        effect2.runTaskLater(plugin, 200L);

        effectThree effect3 = new effectThree(plugin, player);
        effect3.runTaskLater(plugin, 300L);

    }

    @EventHandler
    public void onPlayerJoin(EntityDamageByEntityEvent event){

        Entity isHitting = event.getDamager();
        Entity gotHit = event.getEntity();

        Bukkit.getServer().getConsoleSender().sendMessage(gotHit + " was hit by " + isHitting);

        EntityType gotHitType = gotHit.getType();
        EntityType isHittingType = isHitting.getType();
        if(ConfigManager.playerscfg.getBoolean("enabled") == true){
            if(gotHitType == EntityType.PLAYER && isHittingType == EntityType.ZOMBIE){
                String namePlayer = gotHit.getName();
                Player player = getPlayer(namePlayer);

                if(ConfigManager.playerscfg.contains(player.getName()) == false){

                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "Player's name is " + player.getName() + "and is not on the list");

                    Random rand = new Random(); //instance of random class
                    int upperbound = 100;
                    int int_random = rand.nextInt(upperbound);

                    double health = player.getHealth();

                    if (health < 10){
                        Bukkit.getServer().getConsoleSender().sendMessage("Player infected beacuse his health is below 5 hearts");
                        startDelays(player);


                    }else{

                        if (int_random < 5){
                            Bukkit.getServer().getConsoleSender().sendMessage("Player infected, random number is " + int_random+ " and player's healt is "+health);

                        }else{
                            Bukkit.getServer().getConsoleSender().sendMessage("Player not infected, random number is " + int_random+ " and player's healt is "+health);


                            player.sendMessage(ChatColor.RED + "You have been hit by a zombie!");
                        }
                    }
                }else{
                    Bukkit.getServer().getConsoleSender().sendMessage("[ThreadGames] Player not on the list");
                }
            }
        }
    }
}
