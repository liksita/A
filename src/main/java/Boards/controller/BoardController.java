package Boards.controller;

import Boards.modell.Board;
import Boards.service.BoardService;
import Game.model.Game;
import Player.model.Player;
import errors.ResponseError;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import static util.JsonUtil.json;

/**
 * Created by m on 25.11.15.
 */
public class BoardController {

	public BoardController(final BoardService boardService) {
		after((req, res) -> {
			res.type("application/json");
		});

		// ===========================================================
		// URl: /boards
		// ===========================================================
		// returns all active boards (both running and joinable)
		get("/boards", (req, res) -> boardService.getBoards(), json());

		// ===========================================================
		// URl: /boards/{gameid}
		// ===========================================================
		// gets the board belonging to the game
		get("boards/:gameid", (req, res) -> {
			// prüfe ob board exiestiert
			Board board = boardService.findBoard(req.params(":gameid"));
			if (board == null) {
				res.status(404);
				return new ResponseError(":( wrong Id");
			}
			return board;
		} , json());

		// ? makes shure there is a board for the gameid with the right game
		// settings
		// bei Erstellung eines neuen Spieles ein neues Spielbrett erzeugt wird
		put("boards/:gameid", (req, res) -> {
			res.status(201);
			return boardService.createBoard(req.params(":gameid"));
		} , json());

		// deletes the board to the game, effectivly ending the game
		delete("boards/:gameid", (req, res) -> {
			return boardService.deleteGame(req.params(":gameid"));
		} , json());

		// ===========================================================
		// URl: /boards/games/:gameid/players
		// ===========================================================
		// returns a list of all player positions
		get("boards/:gameid/players", (req, res) -> {
			// prüfe ob board exiestiert
			Board board = boardService.findBoard(req.params(":gameid"));
			if (board == null) {
				res.status(404);
				return new ResponseError(":( wrong Id");
			}
			res.status(200);
			return boardService.getPlayers(req.params(":gameid"));
		} , json());

		// ===========================================================
		// URl: /boards/games/:gameid/players/:playerid
		// ===========================================================
		// places a players: bei Registrierung von Spielern diese gleich auf das
		// Board setzten
		put("boards/games/:gameid/players/:playerid", (req, res) -> {
			// prüfe ob spiel exiestiert
			Board board = boardService.findBoard(req.params(":gameid"));
			if (board == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			// prüfe ob spieler mit gleiche ID schon gesetzt ist
			Player player = boardService.addPlayer(req.params(":gameid"), req.params(":playerid"));
			if (player == null) {
				res.status(400);
				return new ResponseError(":( player with same ID exist");
			}

			// wurde geklappt
			return player;
		} , json());

		// removes a player from the board
		delete("boards/games/:gameid/players/:playerid", (req, res) -> {
			// prüfe ob spiel exiestiert
			Board board = boardService.findBoard(req.params(":gameid"));
			if (board == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			// prüfe ob spieler mit gleiche ID schon gesetzt ist
			Player player = boardService.getPlayer(req.params(":gameid"), req.params(":playerid"));
			if (player == null) {
				res.status(404);
				return new ResponseError(":( wrong player ID");
			}

			// wurde geklappt
			return boardService.deletePlayer(req.params(":gameid"), req.params(":playerid"));
		} , json());

		// Gets a players
		get("boards/games/:gameid/players/:playerid", (req, res) -> {
			// prüfe ob spiel exiestiert
			Board board = boardService.findBoard(req.params(":gameid"));
			if (board == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			// prüfe ob spieler mit gleiche ID schon gesetzt ist
			Player player = boardService.getPlayer(req.params(":gameid"), req.params(":playerid"));
			if (player == null) {
				res.status(404);
				return new ResponseError(":( wrong player ID");
			}

			// wurde geklappt
			return player;
		} , json());

		// ===========================================================
		// URl: /boards/{gameid}/players/{playerid}/move
		// ===========================================================
		// moves a player relative to its current position
		post("boards/games/:gameid/players/:playerid/move", (req, res) -> {
			// prüfe ob spiel exiestiert
			Board board = boardService.findBoard(req.params(":gameid"));
			if (board == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			// prüfe ob spieler mit gleiche ID schon gesetzt ist
			Player player = boardService.getPlayer(req.params(":gameid"), req.params(":playerid"));
			if (player == null) {
				res.status(404);
				return new ResponseError(":( wrong player ID");
			}

			return boardService.movePlayer(req.params(":gameid"), req.params(":playerid"));
		} , json());

		// ===========================================================
		// URl: /boards/{gameid}/players/{playerid}/roll
		// ===========================================================
		// gives a throw of dice from the player to the board
		post("boards/games/:gameid/players/:playerid/move", (req, res) -> {
			// prüfe ob spiel exiestiert
			Board board = boardService.findBoard(req.params(":gameid"));
			if (board == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			// prüfe ob spieler mit gleiche ID schon gesetzt ist
			Player player = boardService.getPlayer(req.params(":gameid"), req.params(":playerid"));
			if (player == null) {
				res.status(404);
				return new ResponseError(":( wrong player ID");
			}

			// wurde geklappt
			return boardService.rollDice(req.params(":gameid"), req.params(":playerid"));
		} , json());

		// ===========================================================
		// URl: /boards/{gameid}/places
		// ===========================================================
		// List of available place
		get("boards/games/:gameid/places", (req, res) -> {
			// prüfe ob spiel exiestiert
			Board board = boardService.findBoard(req.params(":gameid"));
			if (board == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			// wurde geklappt
			return boardService.getAvailableFields(req.params(":gameid"));
		} , json());
		
		// ===========================================================
		// URl: /boards/{gameid}/places/{place}
		// ===========================================================
		//  Gets a places 
		get("boards/games/:gameid/places/:place", (req, res) -> {
			// prüfe ob spiel exiestiert
			Board board = boardService.findBoard(req.params(":gameid"));
			if (board == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			res.status(200);
			// wurde geklappt
			return boardService.getField(req.params(":gameid"), req.params(":place"));
		} , json());
		
		//   places a places 
		put("boards/games/:gameid/places/:place", (req, res) -> {
			// prüfe ob spiel exiestiert
			Board board = boardService.findBoard(req.params(":gameid"));
			if (board == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}
			
			res.status(200);
			// wurde geklappt
			return boardService.setField(req.params(":gameid"), req.params(":place"));
		} , json());

	}
}
