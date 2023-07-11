package me.magicmceu.godwarsplugin.game.api.lobby.commands;

import me.magicmceu.godwarsplugin.GodWars;
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
            switch (args.length){
                case 0:
                    Location loc = p.getLocation();
                    config.set("lobby.name", p.getWorld().getName());
                    config.set(location + "x", loc.getX());
                    config.set(location + "y", loc.getY());
                    config.set(location + "z", loc.getZ());
                    config.set(location + "yaw", loc.getYaw());
                    config.set(location + "pitch", loc.getPitch());
                    GodWars.plugin.saveConfig();
                    p.sendMessage(ChatColor.AQUA + "GodWars Lobby successfully set at " + loc.getX() + " x " + loc.getY() + " y " + loc.getZ() + " z with current facing.");
                    break;
                default:
                    p.sendMessage(ChatColor.RED + "This command does not take any arguments.");
            }
        } else {
            System.out.println("This command cannot be used in console.");
        }
        return false;
    }
}
