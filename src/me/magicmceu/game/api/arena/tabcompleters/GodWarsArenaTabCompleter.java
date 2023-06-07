package me.magicmceu.game.api.arena.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GodWarsArenaTabCompleter implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if(args.length == 1) {
            List<String> GWArenaArgs1 = new ArrayList<>();
            GWArenaArgs1.add("add");
            GWArenaArgs1.add("finish");
            return GWArenaArgs1;
        }
        return null;
    }

}
