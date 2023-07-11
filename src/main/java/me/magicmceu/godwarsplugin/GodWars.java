package me.magicmceu.godwarsplugin;

import me.magicmceu.godwarsplugin.configuration.ConfigurationHelper;
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
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class GodWars extends JavaPlugin {
    public static GodWars plugin;
    public static Logger logger;
    public static ConfigurationHelper config;
    private DivineItemDataBase divineDataBase;
    public static ArenaDataBase arenaDataBase;
    private BukkitAudiences adventure;

    public BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onEnable() {
        adventure = BukkitAudiences.create(this);
        plugin = this;
        logger = getLogger();
        config = new ConfigurationHelper();

        divineDataBase = new DivineItemDataBase();
        AddDivineToBase.AddToBase(divineDataBase);
        arenaDataBase = new ArenaDataBase();

        SetCommandsExecutors();
        RegisterEvents();
        SetTabCompleters();
    }

    @Override
    public void onDisable() {
        config.save();

        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }

        logger.info("GodWars has been disabled");
    }

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
