package me.magicmceu.game.api.arena.commands;

import me.magicmceu.GodWarsPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GodWarsArena implements CommandExecutor {

    static FileConfiguration config = GodWarsPlugin.plugin.getConfig();

    private boolean shouldBreakCheckingMaps;

    private String cfgLoc = "maps.";
    private Boolean canMake;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        cfgLoc = "maps.";
        canMake = Boolean.TRUE;
        if(sender instanceof Player) {
            Player p = (Player) sender;
            cfgLoc = cfgLoc + p.getWorld().getName();
                    switch (args.length){
                case 0:
                    p.sendMessage("Usage:"); //TODO: Usage message
                    break;
                case 1:
                    switch (args[0]){
                        case "add":
                            p.sendMessage("Usage: /gwarena add <arena-name>");
                            break;
                        case "finish":
                            if(!config.contains(cfgLoc)) {
                                p.sendMessage(ChatColor.YELLOW + "This arena does not exist yet, to create use /gwarena add <arena-name>");
                                break;
                            }
                            if(config.getBoolean(cfgLoc + ".finished") == true){
                                p.sendMessage(ChatColor.YELLOW + "This map is already finished");
                            }
                            break;
                        default:
                            p.sendMessage(ChatColor.RED + "Invalid argument.");
                    }
                    break;
                case 2:
                    switch (args[0]){
                        case "add":
                            if(config.contains(cfgLoc)) {
                                p.sendMessage(ChatColor.RED + "There is already arena bind to this world.");
                                break;
                            }
                            shouldBreakCheckingMaps = false;
                            config.getConfigurationSection("maps").getKeys(false).forEach(key -> {
                                if(config.getString("maps." + key + ".name") == args[1] && !shouldBreakCheckingMaps){
                                    p.sendMessage(ChatColor.RED + "This arena name is taken.");
                                    shouldBreakCheckingMaps = true;
                                }
                            });
                            if(shouldBreakCheckingMaps) break;
                            config.set(cfgLoc + ".name", args[1]);
                            config.set(cfgLoc + ".finished", false);
                            config.set(cfgLoc + ".test", null);
                            GodWarsPlugin.plugin.saveConfig();
                            if(config.get(cfgLoc + ".test") == null) p.sendMessage("Dziala");
                            break;
                    }
                    break;
                default:
                    p.sendMessage(ChatColor.RED + "Too many arguments.");
            }
        } else {
            System.out.println("This command cannot be used in console.");
        }
        return true;
    }
}
