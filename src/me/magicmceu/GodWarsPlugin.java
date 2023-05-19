package me.magicmceu;

import me.magicmceu.commands.VortexCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class GodWarsPlugin extends JavaPlugin {

    public static GodWarsPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        this.getCommand("vortex").setExecutor(new VortexCommand());
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling GodWars.");
    }
}
