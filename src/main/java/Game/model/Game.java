package Game.model;

import java.util.ArrayList;
import java.util.List;

import Player.model.Player;

/**
 * - game: |
 * {
 * "type":    "object",
 * "$schema": "http://json-schema.org/draft-03/schema",
 * "id":      "game",
 * "properties": {
 * "gameID": { "type":    "string", "required": true},
 * "players":{ "type":    "array",
 * "items":   {"$ref": "player"} },
 * "components": { "$ref": "components" }
 * }
 * }
 * example: |
 * {"gameID":"42",
 * "players":[{"id":"mario","name":"Mario","uri":"http://localhost:4567/player/mario","ready":false}],
 * "started":false
 * }
 */

public class Game {

    private String gameID;
    private List<Player> players;
    private Components components = null;
    private boolean started = false;

    public Game(String gameID, ArrayList<Player> players, Components components) {
        this.gameID = gameID;
        this.players = players;
        this.components = components;
    }

    public Game(String gameID) {
        this.gameID = gameID;
        this.players = new ArrayList<>();
    }

    public String getGameID() {
        return gameID;
    }

    public boolean readyToStart() {
        if (players.size() < 2) {
            return false;
        } else if (started) {
            return true;
        } else {
            for (Player player : players) {
                if (!player.getReady()) return false;
            }
        }
        started = true;
        return started;
    }

    //==================================================================
    // Player
    //==================================================================
    public List<Player> getPlayers() {
        return players;
    }

    public boolean addPlayer(String playerID) {
        if (getPlayer(playerID) == null) {
            players.add(new Player(playerID));
            return true;
        }
        return false;
    }

    public Player getPlayer(String playerID){
        for (Player player : getPlayers()) {
            if (player.getPlayerID().equals(playerID)) {
                return player;
            }
        }
        return null;
    }

    public boolean deletePlayer(String playerID) {
        Player player = getPlayer(playerID);
        return players.remove(player);
    }

    //==================================================================
    // Components
    //==================================================================
    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }
}

