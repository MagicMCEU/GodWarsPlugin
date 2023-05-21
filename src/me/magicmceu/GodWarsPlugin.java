package me.magicmceu;

import me.magicmceu.commands.VortexCommand;
import me.magicmceu.listeners.VortexListener;
import me.magicmceu.utils.AddDivineToBase;
import me.magicmceu.utils.DivineItemDataBase;
import org.bukkit.plugin.java.JavaPlugin;

public class GodWarsPlugin extends JavaPlugin {

    public static GodWarsPlugin plugin;

    private DivineItemDataBase divineDataBase;


    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        divineDataBase = new DivineItemDataBase();
        AddDivineToBase.AddToBase(divineDataBase);
        this.getCommand("vortex").setExecutor(new VortexCommand());
        this.getServer().getPluginManager().registerEvents(new VortexListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling GodWars.");
    }
}
