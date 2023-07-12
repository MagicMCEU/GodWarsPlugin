package me.magicmceu.godwarsplugin.command;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandsRegistrant {

  private static CommandsRegistrant instance = null;
  public static CommandsRegistrant getInstance() {
    if (instance == null) {
      instance = new CommandsRegistrant();
    }
    return instance;
  }

  private CommandsRegistrant() {}

  public void registerCommands(JavaPlugin plugin) {
    new FlingCommand().registerTo(plugin);
  }
}