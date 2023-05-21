package me.magicmceu.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.List;

public class DivineItem {

    private String name;
    private String inGameName;
    private Material material;
    private List<Enchantment> enchants;

    private List<Integer> enchantLevels;

    public DivineItem(String name, String inGameName, Material material, List<Enchantment> enchants, List<Integer> enchantLevels) {
        this.name = name;
        this.inGameName = inGameName;
        this.material = material;
        this.enchants = enchants;
        this.enchantLevels = enchantLevels;
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
}
