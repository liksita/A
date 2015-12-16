package Boards.modell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	private String gameid;

	private List<Field> fields = new ArrayList<>();
	private List<String> players = new ArrayList<>();
	private Map<String, Integer> positions = new HashMap<>();
	private String uri;

	public Board(String gameid) {
		this.gameid = gameid;
	}

	public String getGameid() {
		return gameid;
	}

	public boolean addPlayer(String playerID) {
		for (String pID : players) {
			if (pID.equals(playerID)) {
				// player exist
				return false;
			}
		}
		return players.add(playerID);
	}

	public boolean removePlayer(String playerid) {
		for (String p : players) {
			if (p.equals(playerid)) {
				return players.remove(p);
			}
		}
		// players not exist
		return false;
	}

	public String getPlayer(String playerid) {
		for (String p : players) {
			if (p.equals(playerid)) {
				return p;
			}
		}
		// player not exist
		return null;
	}

	public List<String> getPlayers() {
		return players;
	}

	public List<Field> getFields() {
		return fields;
	}

	public Field findPlace(String place) {
		for (Field f : fields) {
			if (f.getPlace().getName().equals(place)) {
				return f;
			}
		}
		// field not exist
		return null;
	}
}
