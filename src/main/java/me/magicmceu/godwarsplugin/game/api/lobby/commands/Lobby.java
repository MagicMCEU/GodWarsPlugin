package me.magicmceu.godwarsplugin.game.api.lobby.commands;

import me.magicmceu.godwarsplugin.GodWars;
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
            switch (args.length){
                case 0:
                    Float yaw = (float) config.getDouble(location + "yaw");
                    Float pitch = (float) config.getDouble(location + "pitch");
                    Location lobby = new Location(Bukkit.getWorld(config.getString("lobby.name")), config.getDouble(location + "x"), config.getDouble(location + "y"), config.getDouble(location + "z"), yaw, pitch);
                    p.sendMessage(ChatColor.GREEN + "Teleporting to lobby...");
                    p.teleport(lobby);
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
