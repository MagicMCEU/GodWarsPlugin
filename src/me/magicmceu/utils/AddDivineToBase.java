package me.magicmceu.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.List;

public class AddDivineToBase {
    public static void AddToBase(DivineItemDataBase base){

        // add items here in this way: base.addItem(new DivineItem(String name_id, String inGameName, Material material, List<Enchantment> enchants, List<Integer> enchantLevels, List<String> itemLore))
        base.addItem(new DivineItem("vortex", (ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.ITALIC +  "Vortex"), Material.BLAZE_POWDER, List.of(Enchantment.DURABILITY, Enchantment.KNOCKBACK), List.of(3, 2), null));
        base.addItem(new DivineItem("inferno", (ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.ITALIC +  "Inferno"), Material.BLAZE_ROD, List.of(Enchantment.FIRE_ASPECT, Enchantment.THORNS), List.of(3, 2), null));
    }



}
