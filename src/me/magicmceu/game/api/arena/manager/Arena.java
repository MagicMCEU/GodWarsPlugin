package me.magicmceu.game.api.arena.manager;

import me.magicmceu.game.manager.GameState;
import me.magicmceu.game.manager.Team;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Arena {

    private Integer ArenaID;
    private String ArenaName;
    private String mapName;
    private GameState gameState;
    private Integer maxPlayers;
    private Integer maxPlayersPerTeam;
    private Map<Team, List<Player>> playersInTeams;

    public Arena(Integer ArenaID, String ArenaName, String mapName, GameState gameState, Integer maxPlayers, Integer maxPlayersPerTeam, Map<Team, List<Player>> playersInTeams){
        this.ArenaID = ArenaID;
        this.ArenaName = ArenaName;
        this.mapName = mapName;
        this.gameState = gameState;
        this.maxPlayers = maxPlayers;
        this.maxPlayersPerTeam = maxPlayersPerTeam;
        this.playersInTeams = playersInTeams;
    }

    public Integer getArenaID() {return ArenaID;}
    public String getArenaName() {return ArenaName;}
    public String getMapName() {return mapName;}
    public GameState getGameState() {return gameState;}
    public Integer getMaxPlayers() {return maxPlayers;}
    public Integer getMaxPlayersPerTeam() {return maxPlayersPerTeam;}
    public Map<Team, List<Player>> getPlayersInTeams() {return playersInTeams;}

}
