package me.magicmceu.game.api.arena.manager;

import me.magicmceu.game.manager.GameState;
import me.magicmceu.game.manager.Team;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Arena {

    private int ArenaID;
    private String ArenaName;
    private String mapName;
    private GameState gameState;
    private int maxPlayers;
    private int maxPlayersPerTeam;
    private HashMap<Team, List<Player>> playersInTeams;

    public Arena(int ArenaID, String ArenaName, String mapName, GameState gameState, int maxPlayers, int maxPlayersPerTeam, HashMap<Team, List<Player>> playersInTeams){
        this.ArenaID = ArenaID;
        this.ArenaName = ArenaName;
        this.mapName = mapName;
        this.gameState = gameState;
        this.maxPlayers = maxPlayers;
        this.maxPlayersPerTeam = maxPlayersPerTeam;
        this.playersInTeams = playersInTeams;
    }

    public int getArenaID() {return ArenaID;}
    public String getArenaName() {return ArenaName;}
    public String getMapName() {return mapName;}
    public GameState getGameState() {return gameState;}
    public void setGameState(GameState gameState) {this.gameState = gameState;}
    public int getMaxPlayers() {return maxPlayers;}
    public int getMaxPlayersPerTeam() {return maxPlayersPerTeam;}
    public HashMap<Team, List<Player>> getPlayersInTeams() {return playersInTeams;}
    public void setPlayersInTeams(HashMap<Team, List<Player>> playersInTeams) {this.playersInTeams = playersInTeams;}

}
