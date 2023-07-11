package me.magicmceu.godwarsplugin.game.api.lobby.commands;

import me.magicmceu.godwarsplugin.GodWars;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetLobby implements CommandExecutor {

    static FileConfiguration config = GodWars.plugin.getConfig();
    String location = "lobby.location.";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                Location loc = p.getLocation();
                config.set("lobby.name", p.getWorld().getName());
                config.set(location + "x", loc.getX());
                config.set(location + "y", loc.getY());
                config.set(location + "z", loc.getZ());
                config.set(location + "yaw", loc.getYaw());
                config.set(location + "pitch", loc.getPitch());
                GodWars.plugin.saveConfig();

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
