package me.magicmceu.godwarsplugin.items.commands;

import me.magicmceu.godwarsplugin.GodWars;
import me.magicmceu.godwarsplugin.items.utils.DivineItemDataBase;
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
            GodWars.logger.info("This command can only be executed by a player.");
        }

        return true;
    }
}
