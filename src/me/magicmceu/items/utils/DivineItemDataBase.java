package me.magicmceu.items.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DivineItemDataBase {
    private static List<DivineItem> divineItems;

    public DivineItemDataBase() {
        divineItems = new ArrayList<>();
    }

    public void addItem(DivineItem divineItem) {
        divineItems.add(divineItem);
    }

    public static void giveDivine(String itemName, Player p) {
        for (DivineItem divineItem : divineItems) {
            if (divineItem.getName().equalsIgnoreCase(itemName)) {
                ItemStack itemToGive = new ItemStack(divineItem.getMaterial());
                PlayerInventory inv = p.getInventory();
                ItemMeta itemMeta = itemToGive.getItemMeta();
                if(divineItem.getEnchants() != null) {
                    if(divineItem.getEnchantLevels() != null) {
                        List<Enchantment> enchants = divineItem.getEnchants();
                        List<Integer> enchantLevels = divineItem.getEnchantLevels();
                        for (int i = 0; i < enchants.size(); i++) {
                            Enchantment enchant = enchants.get(i);
                            Integer enchantLevel = enchantLevels.get(i);
                            itemMeta.addEnchant(enchant, enchantLevel, true);
                        }
                    }
                }
                itemMeta.setDisplayName(divineItem.getInGameName());
                if(divineItem.getItemLore() != null) {
                    itemMeta.setLore(divineItem.getItemLore());
                }
                itemToGive.setItemMeta(itemMeta);
                inv.addItem(itemToGive);
                return;  // Zakończ funkcję po znalezieniu pasującego przedmiotu
            }
        }
        System.out.println("Przedmiot o nazwie " + itemName + " nie istnieje w bazie.");
    }

    public static String getDivineName(String itemName) {
        for (DivineItem divineItem : divineItems) {
            if (divineItem.getName().equalsIgnoreCase(itemName)) {
                return divineItem.getInGameName();
            }
        }
        return null;
    }

    public static Material getDivineMaterial(String itemName) {
        for (DivineItem divineItem : divineItems) {
            if (divineItem.getName().equalsIgnoreCase(itemName)) {
                return divineItem.getMaterial();
            }
        }
        return null;
    }
}
