package me.magicmceu.godwarsplugin.items.listeners;


import me.magicmceu.godwarsplugin.items.utils.DivineItemDataBase;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class InfernoListener implements Listener {

    String infernoName = DivineItemDataBase.getDivineName("inferno");
    Material infernoMaterial = DivineItemDataBase.getDivineMaterial("inferno");
    HashMap<Player, Long> rightInfernoCooldown = new HashMap<Player, Long>();
    HashMap<Player, Long> leftInfernoCooldown = new HashMap<Player, Long>();
    Double rInfernoCooldownTime;
    Double lInfernoCooldownTime;
    @EventHandler(priority = EventPriority.HIGH)
    public void onInfernoClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack stack = e.getItem();
        if (stack != null) {
            if (stack.getItemMeta().getDisplayName() != null) {
                if (stack.getType().equals(infernoMaterial) && stack.getItemMeta().getDisplayName().equals(infernoName) && stack != null && stack.getItemMeta().getDisplayName() != null) {
                    if (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) {
                        if (leftInfernoCooldown.containsKey(p)) {
                            if (leftInfernoCooldown.get(p) > System.currentTimeMillis()) {
                                lInfernoCooldownTime = (double) leftInfernoCooldown.get(p);
                                lInfernoCooldownTime = (double) lInfernoCooldownTime - System.currentTimeMillis();
                                lInfernoCooldownTime = (double) lInfernoCooldownTime / 1000;
                                lInfernoCooldownTime = (double) (Math.round(lInfernoCooldownTime * 10.0) / 10.0);
                                p.sendMessage("§7You need to wait §e" + lInfernoCooldownTime + " sec §7before using " + ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "Heart Attack");

                            } else {
                                InfernoMagic(p, "l");
                            }
                        } else {
                            InfernoMagic(p, "l");
                        }
                    }
                    if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
                        if(rightInfernoCooldown.containsKey(p)) {
                            if(rightInfernoCooldown.get(p) > System.currentTimeMillis()) {
                                rInfernoCooldownTime = (double) rightInfernoCooldown.get(p);
                                rInfernoCooldownTime = (double) rInfernoCooldownTime-System.currentTimeMillis();
                                rInfernoCooldownTime = (double) rInfernoCooldownTime/1000;
                                rInfernoCooldownTime = (double) (Math.round(rInfernoCooldownTime*10.0)/10.0);
                                p.sendMessage("§7You need to wait §e"+rInfernoCooldownTime+" sec §7before using " + ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "Meteor Rain");
                            } else {
                                InfernoMagic(p, "r");
                            }
                        } else {
                            InfernoMagic(p, "r");
                        }
                    }
                }
            }
        }
    }

    public void InfernoMagic(Player p , String click){
        if (click == "l") {
            leftInfernoCooldown.put(p, System.currentTimeMillis() + 20000);
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 2.0f, 0.8f);
            List<Object> entities = new ArrayList<>();
            for(double i = 1; i <= 23; i = i + 0.5)
            {
                Location heartCenter = p.getEyeLocation();
                Vector directionC = p.getLocation().getDirection(); // player's direction, a Vector
                heartCenter.add(directionC.multiply(i));
                p.getWorld().spawnParticle(Particle.HEART, heartCenter, 1);
                ArrayList<Entity> centerNearbyEntities = (ArrayList<Entity>) heartCenter.getWorld().getNearbyEntities(heartCenter, 1, 1, 1);
                for(Entity en : p.getWorld().getEntities())
                {
                    if(centerNearbyEntities.contains(en)) {
                        if(en instanceof LivingEntity && en != (Entity) p) {
                            if(!entities.contains(en.getUniqueId())) {
                                if(en.getCustomName() != null) {
                                    if(!en.getCustomName().equals(ChatColor.YELLOW + "ShopKeeper")) {
                                        ((LivingEntity) en).setNoDamageTicks(0);
                                        ((LivingEntity) en).damage(10, p);

                                        entities.add(en.getUniqueId());
                                    }
                                } else{
                                    ((LivingEntity) en).setNoDamageTicks(0);
                                    ((LivingEntity) en).damage(10, p);
                                    entities.add(en.getUniqueId());
                                }
                            }
                        }
                    }
                }
                for(int io = 1; io <= 20; io++)
                {
                    Location heartSpawn = heartCenter;
                    heartSpawn.add(new Vector(getRandomNumber(-0.45, 0.45)*getRandomNumber(0,1)*Math.random(),getRandomNumber(-0.45, 0.45)*getRandomNumber(0,1)*Math.random(),getRandomNumber(-0.45, 0.45)*getRandomNumber(0,1)*Math.random()));
                    p.getWorld().spawnParticle(Particle.HEART, heartSpawn, 1);

                }
            }

        }else if(click == "r") {
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 2.0f, 0.8f);
            List<Object> entities = new ArrayList<>();
            for(double i = 1; i <= 23; i = i + 0.5)
            {
                Location smokeCenter = p.getEyeLocation();
                Vector directionC = p.getLocation().getDirection(); // player's direction, a Vector
                smokeCenter.add(directionC.multiply(i));
                p.getWorld().spawnParticle(Particle.LAVA, smokeCenter, 1);
                ArrayList<Entity> centerNearbyEntities = (ArrayList<Entity>) smokeCenter.getWorld().getNearbyEntities(smokeCenter, 1, 1, 1);
                for(Entity en : p.getWorld().getEntities())
                {
                    if(centerNearbyEntities.contains(en)) {
                        if(en instanceof LivingEntity && en != (Entity) p) {
                            if(!entities.contains(en.getUniqueId())) {
                                if(en.getCustomName() != null) {
                                    if(!en.getCustomName().equals(ChatColor.YELLOW + "ShopKeeper")) {
                                        ((LivingEntity) en).setNoDamageTicks(0);
                                        ((LivingEntity) en).damage(5, p);
                                        ((LivingEntity) en).setFireTicks(140);
                                        entities.add(en.getUniqueId());
                                    }
                                } else{
                                    ((LivingEntity) en).setNoDamageTicks(0);
                                    ((LivingEntity) en).damage(5, p);
                                    ((LivingEntity) en).setFireTicks(140);
                                    entities.add(en.getUniqueId());
                                }
                            }
                        }
                    }
                }
                for(int io = 1; io <= 10; io++)
                {
                    Location smokeSpawn = smokeCenter;
                    smokeSpawn.add(new Vector(getRandomNumber(-1.0, 1.0),getRandomNumber(-1.0, 1.0),getRandomNumber(-1.0, 1.0)).multiply(getRandomNumber(0.01, 0.4)));
                    p.getWorld().spawnParticle(Particle.LAVA, smokeSpawn, 1);

                }
            }
            rightInfernoCooldown.put(p, System.currentTimeMillis() + 20000);
        }
    }
    public double getRandomNumber(double min, double max) {
        double generatedDouble = min + new Random(new Random().nextInt() * 75943).nextDouble() * (max - min);
        return generatedDouble;

    }

}
