package Boards.service;

import util.ConfigReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import Boards.modell.*;
import Dice.model.Roll;
import Game.model.Game;
import Player.model.Player;

/**
 * Created by m on 25.11.15.
 */
public class BoardService {
	private static ArrayList<Board> boards = new ArrayList<>();

	// ===========================================
	// Board's
	// ===========================================
	public ArrayList<Board> getBoards() {
		return boards;
	}

	public Board createBoard(String gameID) {
		Board board = new Board(gameID);
		boards.add(board);
		return board;
	}

	public Board findBoard(String gameID) {
		for (Board board : boards) {
			if (board.getGameid().equals(gameID))
				return board;
		}
		return null;
	}

	// ===========================================
	// Player's
	// ===========================================
	public Player addPlayer(String gameId, String playerID) {
		Board board = findBoard(gameId);
		if (board.addPlayer(playerID)) {
			// TODO frage GameController nach Player nach
			// return Player(playerID);
		}
		// player exist
		return null;
	}

	public Player getPlayer(String gameId, String playerID) {
		Board board = findBoard(gameId);
		if (board != null) {
			// TODO frage GameController nach Player nach
			// return Player(playerID);
		}
		// player not exist
		return null;
	}

	public Object getPlayers(String gameId) {
		Board board = findBoard(gameId);
		if (board != null) {
			// TODO frage GameControler nach alle Players nach
		}
		// player exist
		return null;
	}

	public boolean deletePlayer(String gameId, String playerID) {
		Board board = findBoard(gameId);
		return board.removePlayer(playerID);

	}
	
	// moves a player relative to its current position
	public Object movePlayer(String gameId, String playerID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// List of available place 
	public Object getAvailableFields(String gameId) {
		Board board = findBoard(gameId);
		List<Field> fields = board.getFields();
		List<Place> places = new ArrayList<>();

		for (Field field : fields) {
			places.add(field.getPlace());
		}

		return places;
	}
	
	public Field getField(String gameId, String place) {
		Board board = findBoard(gameId);
		return board.findPlace(place);
	}
	
	public Object setField(String params, String params2) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Roll> rollDice(String params, String params2) {
		// TODO frage DiceService an
		return null;
	}

	
	// ===========================================
	// The End
	// ===========================================
	public Object deleteGame(String gameID) {
		for (Board board : boards) {
			if (board.getGameid().equals(gameID)) {
				boards.remove(board);
				// TODO: remove Game and Bank
				// aber Es gibt keine Methode Spiel zu löschen vorgesehen
			}
		}
		return null;
	}

	

	

}
