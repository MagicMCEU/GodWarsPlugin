package me.magicmceu.godwarsplugin.command;

import me.magicmceu.godwarsplugin.GodWars;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

class ExecutorAdapter implements CommandExecutor, TabCompleter {

  private static final Component DEFAULT_MESSAGE = Component.text("Nieznana komenda. Użyj /help, aby uzyskać pomoc.");
  private static final Component PERMISSION_MESSAGE = Component.text("Nie masz uprawnień do użycia tej komendy.").color(TextColor.color(0xff5555));

  private final String commandString;
  private final List<Parameter> parameters;

  ExecutorAdapter(String commandString) {
    this.parameters = new ArrayList<>();
    this.commandString = commandString;
  }

  void addParameter(Parameter parameter) throws IllegalArgumentException {
    if (parameters.size() > 0) {
      final Parameter lastParameter = parameters.get(parameters.size() - 1);
      if (!lastParameter.isRequired() && parameter.isRequired()) {
        throw new IllegalArgumentException("Wymagany argument nie może być po opcjonalnym argumencie!");
      }
    }
    parameters.add(parameter);
  }

  void registerTo(JavaPlugin plugin) {
    final PluginCommand command = plugin.getCommand(commandString);
    command.setExecutor(this);
    command.setTabCompleter(this);

    command.setUsage("Usage: /" + commandString + " " + parameters.stream().map(Parameter::toString).reduce("", (a, b) -> a + " " + b));
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    try {
      final int index = args.length - 1;
      final Parameter parameter = parameters.get(index);
      return parameter.onComplete(args[index], sender);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!command.testPermissionSilent(sender)) {
      GodWars.plugin.adventure().sender(sender).sendMessage(PERMISSION_MESSAGE);
      return true;
    }
    //if (args.length > parameters.size()) return false;
    if (args.length < parameters.size()) {
      if (args.length <= 0 || parameters.get(args.length - 1).isRequired()) {
        if (parameters.get(args.length).isRequired()) return false;
      }
    }
    return (sender instanceof Player) ? executePlayer((Player)sender, args) : executeCommand(sender, args);
  }

  boolean executePlayer(Player sender, String[] args) {
    GodWars.plugin.adventure().sender(sender).sendMessage(DEFAULT_MESSAGE);
    return true;
  }

  boolean executeCommand(CommandSender sender, String[] args) {
    GodWars.plugin.adventure().sender(sender).sendMessage(DEFAULT_MESSAGE);
    return true;
  }
}