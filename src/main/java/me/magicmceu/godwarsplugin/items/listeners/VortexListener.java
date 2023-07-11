package me.magicmceu.godwarsplugin.items.listeners;

import me.magicmceu.godwarsplugin.items.utils.DivineItemDataBase;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;


public class VortexListener implements Listener {
    String vortexName = DivineItemDataBase.getDivineName("vortex");
    Material vortexMaterial = DivineItemDataBase.getDivineMaterial("vortex");
    HashMap<Player, Long> rightVortexCooldown = new HashMap<Player, Long>();
    HashMap<Player, Long> leftVortexCooldown = new HashMap<Player, Long>();
    HashMap<Player, Boolean> vortexAntiFall = new HashMap<Player, Boolean>();
    Double rVortexCooldownTime;
    Double lVortexCooldownTime;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack stack = e.getItem();
        if (stack != null) {
            if (stack.getItemMeta().getDisplayName() != null) {
                if (stack.getType().equals(vortexMaterial) && stack.getItemMeta().getDisplayName().equals(vortexName) && stack != null && stack.getItemMeta().getDisplayName() != null) {
                    if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
                        if (rightVortexCooldown.containsKey(p)) {
                            if (rightVortexCooldown.get(p) > System.currentTimeMillis()) {
                                rVortexCooldownTime = (double) rightVortexCooldown.get(p);
                                rVortexCooldownTime = (double) rVortexCooldownTime - System.currentTimeMillis();
                                rVortexCooldownTime = (double) rVortexCooldownTime / 1000;
                                rVortexCooldownTime = (double) (Math.round(rVortexCooldownTime * 10.0) / 10.0);
                                p.sendMessage("§7You need to wait §e" + rVortexCooldownTime + " sec §7before using " + vortexName);
                            } else {
                                VortexMagic(p, "r");
                            }
                        } else {
                            VortexMagic(p, "r");
                        }
                    }
                    if (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) {
                        if (leftVortexCooldown.containsKey(p)) {
                            if (leftVortexCooldown.get(p) > System.currentTimeMillis()) {
                                lVortexCooldownTime = (double) leftVortexCooldown.get(p);
                                lVortexCooldownTime = (double) lVortexCooldownTime - System.currentTimeMillis();
                                lVortexCooldownTime = (double) lVortexCooldownTime / 1000;
                                lVortexCooldownTime = (double) (Math.round(lVortexCooldownTime * 10.0) / 10.0);
                                p.sendMessage("§7You need to wait §e" + lVortexCooldownTime + " sec §7before using " + ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "Fire Ball");
                            } else {
                                VortexMagic(p, "l");
                            }
                        } else {
                            VortexMagic(p, "l");
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamageEvent(EntityDamageEvent e){

        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return; // not fall damage.
        }

        if (!(e.getEntity() instanceof Player)) {
            return; // not a player
        }
        Player p = (Player) e.getEntity();
        if (vortexAntiFall.get(p) == true) {
            e.setCancelled(true);
            vortexAntiFall.put(p, false);
            vortexAntiFall.replace(p, false);
            p.sendMessage(ChatColor.AQUA + "Vortex saved you from fall damage!");
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
        Entity d = e.getDamager();
        Entity s = e.getEntity();
        if(d instanceof Fireball){
            e.setDamage(e.getFinalDamage()*3);
        }
    }

    public void VortexMagic (Player p, String click){
        if (click == "r") {
            p.getWorld().playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 2.0f, 1.0f);
            Vector xzVector = new Vector(p.getLocation().getDirection().getX(), 0, p.getLocation().getDirection().getZ());
            xzVector = xzVector.normalize();
            Vector yVector = new Vector(0, 10, 0);
            yVector = yVector.normalize();
            p.setVelocity(xzVector.multiply(7).add(yVector));
            vortexAntiFall.put(p, true);
            vortexAntiFall.replace(p, true);
            rightVortexCooldown.put(p, System.currentTimeMillis() + 10000);
            vortexCircle(30, 1.0d, p);
        } else if (click == "l") {
            p.getWorld().playSound(p.getLocation(), Sound.GHAST_FIREBALL, 2.0f, 1.2f);
            Fireball fireball = p.launchProjectile(Fireball.class, p.getLocation().getDirection().normalize());
            fireball.setVelocity(fireball.getVelocity().multiply(2));
            fireball.setIsIncendiary(false);
            fireball.setYield(0);
            fireball.setCustomName(ChatColor.AQUA + "VortexBall");
            leftVortexCooldown.put(p, System.currentTimeMillis() + 10000);
        }
    }

    public void vortexCircle ( int points, double radius, Player p){
        Location origin = p.getLocation();

        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / points;
            Location point = origin.clone().add(radius * Math.sin(angle), 0.0d, radius * Math.cos(angle));
            p.getWorld().playEffect(point, Effect.SPELL, 4);
        }
    }
}
