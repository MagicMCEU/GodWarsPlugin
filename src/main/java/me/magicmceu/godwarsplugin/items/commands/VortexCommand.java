package me.magicmceu.godwarsplugin.items.commands;

import me.magicmceu.godwarsplugin.GodWars;
import me.magicmceu.godwarsplugin.items.utils.DivineItemDataBase;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VortexCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                GodWars.plugin.adventure().player(p).sendMessage(
                        Component.text("You've received the ")
                                .color(TextColor.color(0x55ffff))
                                .append(
                                        Component.text("Vortex")
                                                .color(TextColor.color(0xffaa00))
                                )
                                .append(Component.text("!"))
                );
                DivineItemDataBase.giveDivine("vortex", p);
            } else {
                GodWars.plugin.adventure().player(p).sendMessage(
                        Component.text("This command doesn't take any arguments.")
                                .color(TextColor.color(0xff5555))
                );
            }
        } else {
            GodWars.logger.info("This command can only be executed by a player.");
        }

        return true;
    }
}
