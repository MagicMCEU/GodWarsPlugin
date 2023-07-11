package me.magicmceu.godwarsplugin.game.api.lobby.commands;

import me.magicmceu.godwarsplugin.GodWars;
import me.magicmceu.godwarsplugin.configuration.configs.Loc;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                GodWars.config.main.lobby = Loc.fromLocation(p.getLocation());
                GodWars.config.save();

                GodWars.plugin.adventure().player(p).sendMessage(
                        Component.text("Lobby location set!")
                                .color(TextColor.color(0x55ff55))
                );
            } else {
                GodWars.plugin.adventure().player(p).sendMessage(
                        Component.text("This command doesn't take any arguments.")
                                .color(TextColor.color(0xff5555))
                );
            }
        } else {
            GodWars.logger.info("This command can only be executed by a player.");
        }
        return false;
    }
}
