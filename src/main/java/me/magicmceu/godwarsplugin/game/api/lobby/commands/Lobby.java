package me.magicmceu.godwarsplugin.game.api.lobby.commands;

import me.magicmceu.godwarsplugin.GodWars;
import me.magicmceu.godwarsplugin.configuration.configs.Loc;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                Loc lobby = GodWars.config.main.lobby;

                if (lobby == null) {
                    GodWars.plugin.adventure().player(p).sendMessage(
                            Component.text("The lobby hasn't been set yet!")
                                    .color(TextColor.color(0xff5555))
                    );
                    return false;
                }

                GodWars.plugin.adventure().player(p).sendMessage(
                        Component.text("Teleporting you to the lobby!")
                                .color(TextColor.color(0x55ff55))
                );

                p.teleport(lobby.toLocation());

                return true;
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
