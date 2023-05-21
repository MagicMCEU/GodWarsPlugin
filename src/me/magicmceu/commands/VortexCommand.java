package me.magicmceu.commands;

import me.magicmceu.utils.DivineItemDataBase;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class VortexCommand implements CommandExecutor {

    String vortexName = (ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.ITALIC +  "Vortex");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            switch (args.length){
                case 0:
                    p.sendMessage(ChatColor.AQUA + "You received Vortex!");
                    DivineItemDataBase.giveDivine("vortex", p);
                    break;
                default:
                    p.sendMessage("This command does not take any arguments.");
            }
        } else {
            System.out.println("This command cannot be used in console.");
        }

        return true;
    }
}
