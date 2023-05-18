package me.magicmceu;

import org.bukkit.plugin.java.JavaPlugin;

public class GodWarsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("This plugin is now running");
    }

    @Override
    public void onDisable() {
        System.out.println("This plugin has stopped running");
    }
}
