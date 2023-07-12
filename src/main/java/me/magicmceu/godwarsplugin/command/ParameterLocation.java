package me.magicmceu.godwarsplugin.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParameterLocation extends Parameter {
  enum Axis {
    X, Y, Z
  }

  private Axis axis;

  ParameterLocation(boolean required, Axis axis) {
    super(required);
    this.axis = axis;
  }

  @Override
  String getName() {
    return this.axis.toString().toLowerCase();
  }

  @Override
  List<String> onComplete(String parameter, CommandSender sender) {
    final List<String> suggestions = new ArrayList<>();
    suggestions.add("~");
    final Player player = (sender instanceof Player) ? (Player)sender : null;
    if (player == null) return suggestions;
    Set<Material> s = new HashSet<>();
    final Block block = player.getTargetBlock(s, 10);
    if (block == null) return suggestions;
    final Location location = block.getLocation();
    switch(axis) {
      case X: {
        Integer a = location.getBlockX();
        suggestions.add(a.toString());
        break;
      }
      case Y: {
        Integer a = location.getBlockY();
        suggestions.add(a.toString());
        break;
      }
      case Z: {
        Integer a = location.getBlockZ();
        suggestions.add(a.toString());
        break;
      }
      default:
        break;
    }
    return suggestions;
  }
  
}
