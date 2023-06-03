package me.magicmceu.game.api.arena.manager;

import java.util.ArrayList;
import java.util.List;

public class ArenaDataBase {
    private static List<Arena> arenas;

    public ArenaDataBase() {arenas = new ArrayList<>();}

    public void addArena(Arena arena) {arenas.add(arena);}

}
