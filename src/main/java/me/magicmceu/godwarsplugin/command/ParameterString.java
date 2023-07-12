package me.magicmceu.godwarsplugin.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public class ParameterString extends Parameter {
    public ParameterString(boolean optional) {
        super(optional);
    }

    @Override
    String getName() {
        return "string";
    }

    @Override
    List<String> onComplete(String parameter, CommandSender sender) {
        return null;
    }
}
