package me.magicmceu.godwarsplugin.game.api.arena.commands;

import me.magicmceu.godwarsplugin.GodWars;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class GodWarsArena implements CommandExecutor {

    static FileConfiguration config = GodWars.plugin.getConfig();

    private List<String> args0 = Arrays.asList("add", "finish");
    private List<String> arenaType = Arrays.asList("solo", "doubles", "triples", "4v4v4v4", "4v4");
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
                    p.sendMessage("Usage: /gwarena add <arena-name> <arena-type>");
                    p.sendMessage("Makes a new GodWars arena and makes it able to edit");
                    p.sendMessage("Usage: /gwarena finish");
                    p.sendMessage("Finishes the arena you are currently editing");
                    break;
                case 1:
                    switch (args[0].toLowerCase()){
                        case "add":
                            p.sendMessage("Usage: /gwarena add <arena-name> <arena-type>");
                            break;
                        case "finish":
                            if(!config.contains(cfgLoc)) {
                                p.sendMessage(ChatColor.YELLOW + "This arena does not exist yet, to create use /gwarena add <arena-name>");
                                break;
                            }
                            if(config.getBoolean(cfgLoc + ".finished")){
                                p.sendMessage(ChatColor.YELLOW + "This map is already finished");
                                break;
                            }
                            p.sendMessage(ChatColor.GREEN + "Map successfully finished");
                            config.set(cfgLoc + ".finished", true);
                            GodWars.plugin.saveConfig();
                            break;
                        default:
                            p.sendMessage(ChatColor.RED + "Invalid argument.");
                    }
                    break;
                case 2:
                    switch (args[0].toLowerCase()){
                        case "add":
                            p.sendMessage("Usage: /gwarena add <arena-name> <arena-type>");
                            break;
                        default:
                            if(args0.contains(args[0].toLowerCase())){
                                p.sendMessage(ChatColor.RED + "Too many arguments.");
                            } else {
                                p.sendMessage(ChatColor.RED + "Invalid arguments.");
                            }
                    }
                    break;
                case 3:
                    switch (args[0].toLowerCase()){
                        case "add":
                            if(config.contains(cfgLoc)) {
                                p.sendMessage(ChatColor.RED + "There is already arena bind to this world.");
                                break;
                            }
                            shouldBreakCheckingMaps = false;
                            if(config.contains("maps")) {
                                config.getConfigurationSection("maps").getKeys(false).forEach(key -> {
                                    if (config.getString("maps." + key + ".name").equals(args[1]) && shouldBreakCheckingMaps == false) {
                                        p.sendMessage(ChatColor.RED + "This arena name is taken.");
                                        shouldBreakCheckingMaps = true;
                                    }
                                });
                            }
                            if(shouldBreakCheckingMaps) break;
                            if(arenaType.contains(args[2].toLowerCase())) {
                                config.set(cfgLoc + ".name", args[1]);
                                config.set(cfgLoc + ".finished", false);
                                config.set(cfgLoc + ".type", args[2]);
                                GodWars.plugin.saveConfig();
                                p.sendMessage(ChatColor.GREEN + "Arena " + args[1] + " has been successfully created.");
                                // if(config.get(cfgLoc + ".test") == null) p.sendMessage("Dziala");
                                break;
                            }
                            p.sendMessage(ChatColor.RED + "This arena type does not exist.");
                            break;
                        default:
                            if(args0.contains(args[0].toLowerCase())){
                                p.sendMessage(ChatColor.RED + "Too many arguments.");
                            } else {
                                p.sendMessage(ChatColor.RED + "Invalid arguments.");
                            }
                    }
                    break;
                default:
                    if(args0.contains(args[0].toLowerCase())){
                        p.sendMessage(ChatColor.RED + "Too many arguments.");
                    } else {
                        p.sendMessage(ChatColor.RED + "Invalid arguments.");
                    }
            }
        } else {
            System.out.println("This command cannot be used in console.");
        }
        return true;
    }
}
