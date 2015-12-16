package Game.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mashape.unirest.http.Unirest;

import Game.model.Game;
import Player.model.Player;

public class GameService {
	private static int gameID = 0;
	private static ArrayList<Game> games = new ArrayList<>();
	private HashMap<String, String> services = new HashMap<>();

	// ==================================================================
	// Game's
	// ==================================================================
	public static String getNextGameID() {
		return String.valueOf(gameID++);
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public Game createGame() {
		String neugameID = getNextGameID();
		Game game = new Game(neugameID);
		try {
			createBoard(neugameID);
			createBank(neugameID);
		} catch (Exception e) {
			System.out.println("Configfile not found");
			e.printStackTrace();
		}
		
		games.add(game);
		return game;
	}

	public void createBoard(String gameID) throws IOException {
		String boardHost = put("boards", gameID);
		Game game = findGame(gameID);
		game.updateComponents("boards", boardHost);
	}
	
	public void createBank(String gameID) throws IOException {
		String bankHost = put("bank", gameID);
		Game game = findGame(gameID);
		game.updateComponents("bank", bankHost);
	}
	
	public Game findGame(String gameID) {
		for (Game game : games) {
			if (game.getGameID().equals(gameID))
				return game;
		}
		return null;
	}

	// ==================================================================
	// Player
	// ==================================================================
	public Game addPlayer(String gameId, String playerID) throws IOException {
		Game game = findGame(gameId);
		if (game.addPlayer(playerID)) {
			String board = game.getComponents().getBoard();
			put("boards", gameId + "/players/" + playerID);
			return game;
		}
		// player exist
		return null;
	}

	public Player getPlayer(String gameId, String playerId) {
		Game game = findGame(gameId);
		return game.getPlayer(playerId);
	}

	public Player setPlayerReady(String gameId, String playerId) {
		Game game = findGame(gameId);
		// wenn spiel nicht gestartet ist, dann registriere player als "ready"
		if (!game.readyToStart()) {
			Player player = getPlayer(gameId, playerId);
			if (player != null) {
				player.setReady();

				// wenn player, der letzte "ready"-player ist, dann kann Spiel
				// starten
				if (game.readyToStart()) {
					// TODO
					// inizialisiere bank & create a bank account's
					// Wenn alle Clients bereit sind, kann das Spiel beginnen –
					// die erste Person muss anfangen zu würfeln!
					// Achten Sie darauf, dass für die verschiedenen
					// Spielkomponenten
					// auch unterschiedliche Hosts
					// über- bzw. angegeben werden können
				}
			}
			return player;
		}
		return null;
	}

	public boolean deletePlayer(String gameID, String playerID) {
		Game game = findGame(gameID);
		return game.deletePlayer(playerID);
	}

	// ==================================================================
	// Components
	// ==================================================================
	
	
	

	// ==================================================================
	// Helpmethod's
	// ==================================================================
	private String put(String service, String request) throws IOException {
		String uri;
		// uri = ConfigReader.getSetting(service);
		uri = "http://locahost/4567";
		String boardHost = uri + "/" + request;
		Unirest.put(boardHost);
		return boardHost;
	}

}