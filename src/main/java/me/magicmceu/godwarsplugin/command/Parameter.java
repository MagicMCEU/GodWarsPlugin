package me.magicmceu.godwarsplugin.command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

abstract class Parameter {
  private final boolean required;

  Parameter(boolean required) {
    this.required = required;
  }

  boolean isRequired() {
    return this.required;
  }

  abstract String getName();

  abstract List<String> onComplete(String parameter, CommandSender sender);
  
  @Override
  public String toString() {
    return (required? ChatColor.RED + "<" + getName() + ">" : ChatColor.GRAY + "[" + getName() + "]");
  }
}