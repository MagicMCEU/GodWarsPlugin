package me.magicmceu.godwarsplugin.command;

import me.magicmceu.godwarsplugin.GodWars;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class FlingCommand extends ExecutorAdapter {
    public FlingCommand() {
        super("fling");
        addParameter(new ParameterPlayer(true));
    }

    @Override
    boolean executePlayer(Player sender, String[] args) {
        return mergedExecute(sender, args);
    }

    @Override
    boolean executeCommand(CommandSender sender, String[] args) {
        return mergedExecute(sender, args);
    }

    private boolean mergedExecute(CommandSender sender, String[] args) {
        final List<Player> players = new PlayerSelector(sender, args[0]).getPlayers();
        if (players == null) {
            GodWars.plugin.adventure().sender(sender).sendMessage(
                    Component.text("No players found matching " + args[0] + ".")
                            .color(TextColor.color(0xff5555))
            );
            return true;
        }

        for (Player player : players) {
            double x = Math.random() * 20 - 10;
            double y = 1.4;
            double z = Math.random() * 20 - 10;
            player.setVelocity(player.getVelocity().add(new Vector(x, y, z)));
            player.setFallDistance(-64F);
        }
        return true;
    }
}
