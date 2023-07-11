package me.magicmceu.godwarsplugin.game.api.lobby.commands;

import me.magicmceu.godwarsplugin.GodWars;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Lobby implements CommandExecutor {

    static FileConfiguration config = GodWars.plugin.getConfig();
    String location = "lobby.location.";


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                float yaw = (float) config.getDouble(location + "yaw", 0);
                float pitch = (float) config.getDouble(location + "pitch", 0);
                double x = config.getDouble(location + "x", 0);
                double y = config.getDouble(location + "y", 200);
                double z = config.getDouble(location + "z", 0);
                String world = config.getString("lobby.name", "world");

                Location lobby = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);

                GodWars.plugin.adventure().player(p).sendMessage(
                        Component.text("Teleporting you to the lobby!")
                                .color(TextColor.color(0x55ff55))
                );

                p.teleport(lobby);
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
