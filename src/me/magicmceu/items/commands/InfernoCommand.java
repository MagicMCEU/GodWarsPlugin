package me.magicmceu.items.commands;

import me.magicmceu.items.utils.DivineItemDataBase;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfernoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            switch (args.length){
                case 0:
                    p.sendMessage(ChatColor.AQUA + "You received Inferno!");
                    DivineItemDataBase.giveDivine("inferno", p);
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
