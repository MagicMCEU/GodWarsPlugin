package me.magicmceu.godwarsplugin;

import me.magicmceu.godwarsplugin.game.api.arena.commands.GodWarsArena;
import me.magicmceu.godwarsplugin.game.api.arena.manager.ArenaDataBase;
import me.magicmceu.godwarsplugin.game.api.arena.tabcompleters.GodWarsArenaTabCompleter;
import me.magicmceu.godwarsplugin.game.api.lobby.commands.Lobby;
import me.magicmceu.godwarsplugin.game.api.lobby.commands.SetLobby;
import me.magicmceu.godwarsplugin.items.commands.InfernoCommand;
import me.magicmceu.godwarsplugin.items.commands.VortexCommand;
import me.magicmceu.godwarsplugin.items.listeners.InfernoListener;
import me.magicmceu.godwarsplugin.items.listeners.VortexListener;
import me.magicmceu.godwarsplugin.items.utils.AddDivineToBase;
import me.magicmceu.godwarsplugin.items.utils.DivineItemDataBase;
import org.bukkit.plugin.java.JavaPlugin;

public final class GodWars extends JavaPlugin {
    public static GodWars plugin;
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
        RegisterEvents();
        SetTabCompleters();
    }

    @Override
    public void onDisable() { System.out.println("Disabling GodWars."); }

    public void RegisterEvents(){
        this.getServer().getPluginManager().registerEvents(new VortexListener(), this);
        this.getServer().getPluginManager().registerEvents(new InfernoListener(), this);
    }

    public void SetCommandsExecutors() {
        this.getCommand("vortex").setExecutor(new VortexCommand());
        this.getCommand("inferno").setExecutor(new InfernoCommand());
        this.getCommand("gwarena").setExecutor(new GodWarsArena());
        this.getCommand("lobby").setExecutor(new Lobby());
        this.getCommand("setgwlobby").setExecutor(new SetLobby());
    }

    public void SetTabCompleters() {
        this.getCommand("gwarena").setTabCompleter(new GodWarsArenaTabCompleter());
    }
}
