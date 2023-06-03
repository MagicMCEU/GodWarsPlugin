package me.magicmceu.items.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.List;

public class DivineItem {

    private String name;
    private String inGameName;
    private Material material;
    private List<Enchantment> enchants;
    private List<Integer> enchantLevels;
    private List<String> itemLore;

    public DivineItem(String name, String inGameName, Material material, List<Enchantment> enchants, List<Integer> enchantLevels, List<String> itemLore) {
        this.name = name;
        this.inGameName = inGameName;
        this.material = material;
        this.enchants = enchants;
        this.enchantLevels = enchantLevels;
        this.itemLore = itemLore;
    }

    public String getName() {
        return name;
    }


    public String getInGameName() {
        return inGameName;
    }

    public Material getMaterial() {
        return material;
    }

    public List<Enchantment> getEnchants() {
        return enchants;
    }


    public List<Integer> getEnchantLevels() {
        return enchantLevels;
    }

    public List<String> getItemLore() { return itemLore; }
}
