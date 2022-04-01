package com.thethreatgames.threatgames.events;

import com.thethreatgames.threatgames.ConfigManager;
import com.thethreatgames.threatgames.Threatgames;
import com.thethreatgames.threatgames.events.infectionEffects.*;
import com.thethreatgames.threatgames.events.infectionEffects.heartFunctions.addHearts;
import com.thethreatgames.threatgames.events.infectionEffects.heartFunctions.takeawayHearts;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
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


    //The function called when the player gets infected
    public void startDelays(Player player){

        //the player is informed that he is infected
        player.sendMessage(ChatColor.RED + "You have been infected! \n To cure yourself eat an golden apple!");
        player.sendMessage(ChatColor.YELLOW + "Overtime, you will get weird effects, don't forget that!");

        //saves the infected player in the config
        ConfigManager.saveInfectedPlayer(player);

        //saves the players helmet, since it get's removed when he gets a pumkin
        player.getInventory().setHelmet(new ItemStack(Material.CARVED_PUMPKIN, 1));

        //instance of bukkitrunnables for effects and hearts
        effectOne effect = new effectOne(plugin, player);
        effect.runTaskLater(plugin, 1200L);

        effectTwo effect2 = new effectTwo(plugin, player);
        effect2.runTaskLater(plugin, 2400L);

        effectThree effect3 = new effectThree(plugin, player);
        effect3.runTaskLater(plugin, 3600L);

        effectFour effect4 = new effectFour(plugin, player);
        effect4.runTaskLater(plugin, 4800L);

        effectFive effect5 = new effectFive(plugin, player);
        effect5.runTaskLater(plugin, 6000L);

        effectSix effect6 = new effectSix(plugin, player);
        effect6.runTaskLater(plugin, 7200L);

        effectSeven effect7 = new effectSeven(plugin, player);
        effect7.runTaskLater(plugin, 8400L);

        playerFullyInfected infectedFunction = new playerFullyInfected(plugin, player);
        infectedFunction.runTaskLater(plugin, 8400L);

        takeawayHearts heartsFunc = new takeawayHearts(plugin, player);
        heartsFunc.runTaskLater(plugin, 1100L);

    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event){

        //Gets the entity that is attacking and who is he/her targeting
        Entity attacker = event.getDamager(); //the entity which is attacking
        Entity defender = event.getEntity(); //the attacked one

        //just gets their types
        EntityType defenderType = defender.getType();
        EntityType attackerType = attacker.getType();


        //checks if the plugin is enabled
        if(ConfigManager.playerscfg.getBoolean("enabled") == true){
            //if the entity that is being attacked is a player
            if(defenderType == EntityType.PLAYER){
                //if the attacker is a zombie
                if(attackerType == EntityType.ZOMBIE){

                    //gets the player object/var
                    String namePlayer = defender.getName();
                    Player player = getPlayer(namePlayer);

                    //if the player that is getting attacked isn't infected
                    if(ConfigManager.playerscfg.contains(player.getName()) == false){

                        //generate a new number for the 5% chance of infection
                        Random rand = new Random(); //instance of random class
                        int upperbound = 100;
                        int int_random = rand.nextInt(upperbound);

                        //gets the player healt
                        double health = player.getHealth();

                        //if the player has less than 5 hearts (10hp)
                        if (health < 10){
                            //calls the function for infecting the player
                            startDelays(player);
                        }else{
                            //if the player doesn't have below five hearts, checks the 5% chance
                            if (int_random < 5){
                                startDelays(player);
                            }
                        }
                    }
                //now it checks if the attacker is an instance of a player
                }else if(attackerType == EntityType.PLAYER){

                    //gets the variables/objects for the attacker and defender
                    String namePlayerHit = defender.getName();
                    Player defenders = getPlayer(namePlayerHit);

                    String namePlayerHitting = attacker.getName();
                    Player attackers = getPlayer(namePlayerHitting);

                    //if the player that is attacking is infected
                    if(ConfigManager.playerscfg.contains(attacker.getName())) {

                        //adds one heart to the infected player attacking
                        String namePlayer = attacker.getName();
                        Player player = getPlayer(namePlayer);

                        addHearts.instantHearts(player);

                        //if the defender is not infected
                        if (!ConfigManager.playerscfg.contains(defender.getName())) {

                            //just runs the same algorithm from before
                            Random rand = new Random(); //instance of random class
                            int upperbound = 100;
                            int int_random = rand.nextInt(upperbound);

                            double health = defenders.getHealth();

                            if (health < 10) {
                                startDelays(defenders);
                            } else {
                                if (int_random < 5) {
                                    startDelays(defenders);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

