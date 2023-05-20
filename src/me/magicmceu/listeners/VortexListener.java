package me.magicmceu.listeners;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;


public class VortexListener implements Listener {


    boolean vortexFall;
    boolean canCheckVortexFall;
    String vortexName = (ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.ITALIC +  "Vortex");
    HashMap<Player, Long> rightVortexCooldown = new HashMap<Player, Long>();
    HashMap<Player, Long> leftVortexCooldown = new HashMap<Player, Long>();
    HashMap<Player, Boolean> vortexAntiFall = new HashMap<Player, Boolean>();
    Double rVortexCooldownTime;
    Double lVortexCooldownTime;
    @EventHandler(priority = EventPriority.HIGH)
    public void onVortexClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack stack = e.getItem();
        if(stack.getType().equals(Material.BLAZE_POWDER) && stack.getItemMeta().getDisplayName().equals(vortexName) && stack!=null && stack.getItemMeta().getDisplayName() != null) {
            if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
                if(rightVortexCooldown.containsKey(p)) {
                    if(rightVortexCooldown.get(p) > System.currentTimeMillis()) {
                        rVortexCooldownTime = (double) rightVortexCooldown.get(p);
                        rVortexCooldownTime = (double) rVortexCooldownTime-System.currentTimeMillis();
                        rVortexCooldownTime = (double) rVortexCooldownTime/1000;
                        rVortexCooldownTime = (double) (Math.round(rVortexCooldownTime*10.0)/10.0);
                        p.sendMessage("§7You need to wait §e"+rVortexCooldownTime+" sec §7before using " + ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "Vortex");
                    } else{
                        VortexMagic(p, "r");
                    }
                } else {
                    VortexMagic(p, "r");
                }
            }
            if (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) {

            }
        } else if(stack==null){

        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onVortexFall(EntityDamageEvent e) {

        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return; // not fall damage.
        }

        if (!(e.getEntity() instanceof Player)) {
            return; // not a player
        }
        Player p = (Player) e.getEntity();
        if(vortexAntiFall.get(p) == true)
        {
            e.setCancelled(true);
            vortexAntiFall.put(p, false);
            vortexAntiFall.replace(p, false);
            p.sendMessage(ChatColor.AQUA + "Vortex saved you from fall damage!");
        }
    }



    public void VortexMagic(Player p, String click){
        if(click=="r") {
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 2.0f, 1.0f);
            Vector xzVector = new Vector(p.getLocation().getDirection().getX(), 0, p.getLocation().getDirection().getZ());
            xzVector = xzVector.normalize();
            Vector yVector = new Vector(0, 10, 0);
            yVector = yVector.normalize();
            p.setVelocity(xzVector.multiply(7).add(yVector));
            vortexAntiFall.put(p, true);
            vortexAntiFall.replace(p, true);
            rightVortexCooldown.put(p, System.currentTimeMillis() + 10000);
            vortexCircle(30, 1.0d, p);
        } else if(click=="l") {

        }
    }

    public void vortexCircle(int points, double radius, Player p) {



        Location origin = p.getLocation();

        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / points;
            Location point = origin.clone().add(radius * Math.sin(angle), 0.0d, radius * Math.cos(angle));
            p.getWorld().spawnParticle(Particle.SPELL_INSTANT, point, 1);
        }
    }

}
