package me.magicmceu.godwarsplugin.game.api.arena.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GodWarsArenaTabCompleter implements TabCompleter {


    private List<String> arenaType = Arrays.asList("solo", "doubles", "triples", "4v4v4v4", "4v4");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if(args.length == 1) {
            List<String> GWArenaArgs1 = new ArrayList<>();
            GWArenaArgs1.add("add");
            GWArenaArgs1.add("finish");
            return GWArenaArgs1;
        } else if(args.length == 3 && args[0].equalsIgnoreCase("add")){
            List<String> GWarenaType = new ArrayList<>();
            GWarenaType = arenaType;
            return GWarenaType;
        } else {
            return List.of("");
        }
    }

}
