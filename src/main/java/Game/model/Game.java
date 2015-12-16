package Game.model;

import java.util.ArrayList;
import java.util.List;

import Player.model.Player;

public class Game {

	private String gameID;
	private List<Player> players;
	private Components components = null;
	private boolean started = false;

	public Game(String gameID) {
		this.gameID = gameID;
		this.players = new ArrayList<>();
		this.components = new Components();
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
				if (!player.getReady())
					return false;
			}
		}
		started = true;
		return started;
	}

	// ==================================================================
	// Player
	// ==================================================================
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

	public Player getPlayer(String playerID) {
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

	// ==================================================================
	// Components
	// ==================================================================
	public Components getComponents() {
		return components;
	}

	public void updateComponents(String service, String serviceHost) {
		if (service.contains("banks")) {
			this.components.setBank(serviceHost);
		} else if (service.contains("boards")) {
			this.components.setBoard(serviceHost);
		} else if (service.contains("brokers")) {
			this.components.setBroker(serviceHost);
		} else if (service.contains("dice")) {
			this.components.setDice(serviceHost);
		} else if (service.contains("games")) {
			this.components.setGame(serviceHost);
		} else if (service.contains("event")) {
			this.components.setEvent(serviceHost);
		} else if (service.contains("decks")) {
			this.components.setDeck(serviceHost);
		}
	}
}
