package me.magicmceu;

import me.magicmceu.game.api.arena.manager.ArenaDataBase;
import me.magicmceu.items.commands.InfernoCommand;
import me.magicmceu.items.commands.VortexCommand;
import me.magicmceu.items.listeners.InfernoListener;
import me.magicmceu.items.listeners.VortexListener;
import me.magicmceu.items.utils.AddDivineToBase;
import me.magicmceu.items.utils.DivineItemDataBase;
import org.bukkit.plugin.java.JavaPlugin;

public class GodWarsPlugin extends JavaPlugin {

    public static GodWarsPlugin plugin;

    private DivineItemDataBase divineDataBase;
    public static ArenaDataBase arenaDataBase;

    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        divineDataBase = new DivineItemDataBase();
        AddDivineToBase.AddToBase(divineDataBase);
        arenaDataBase = new ArenaDataBase();
        this.getCommand("vortex").setExecutor(new VortexCommand());
        this.getCommand("inferno").setExecutor(new InfernoCommand());
        this.getServer().getPluginManager().registerEvents(new VortexListener(), this);
        this.getServer().getPluginManager().registerEvents(new InfernoListener(), this);
    }

    @Override
    public void onDisable() { System.out.println("Disabling GodWars."); }
}
