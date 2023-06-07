package me.magicmceu;

import me.magicmceu.game.api.arena.commands.GodWarsArena;
import me.magicmceu.game.api.arena.manager.ArenaDataBase;
import me.magicmceu.game.api.lobby.commands.Lobby;
import me.magicmceu.game.api.lobby.commands.SetLobby;
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
        SetCommandsExecutors();
        this.getServer().getPluginManager().registerEvents(new VortexListener(), this);
        this.getServer().getPluginManager().registerEvents(new InfernoListener(), this);
    }

    @Override
    public void onDisable() { System.out.println("Disabling GodWars."); }


    public void SetCommandsExecutors() {
        this.getCommand("vortex").setExecutor(new VortexCommand());
        this.getCommand("inferno").setExecutor(new InfernoCommand());
        this.getCommand("gwarena").setExecutor(new GodWarsArena());
        this.getCommand("lobby").setExecutor(new Lobby());
        this.getCommand("setgwlobby").setExecutor(new SetLobby());
    }
}
