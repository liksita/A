package Game.controller;

import errors.ResponseError;

import Game.model.Game;
import Player.model.Player;
import Game.service.GameService;

import static spark.Spark.*;
import static util.JsonUtil.json;

//import com.sun.tools.javac.util.List;

public class GameController {

	public GameController(final GameService gameService) {

		after((req, res) -> {
			res.type("application/json");
		});

		// ===========================================================
		// URI: /games
		// ===========================================================
		// returns all available games 
		get("/games", (req, res) -> {
			res.status(200);
			return gameService.getGames();
		} , json());

		//  creates a new game 
		post("/games", (req, res) -> {
			Game game = gameService.createGame();

			// ToDo: bei Erstellung eines neuen Spieles ein neues Spielbrett
			// erzeugt durch put/boards/{gameid}

			res.status(201);
			return game;
		} , json());

		// ===========================================================
		// URI: /games/{gameid}
		// ===========================================================
		//  returns the current game status 
		get("/games/:gameid", (req, res) -> {
			// prüfe ob spiel exiestiert
			Game game = gameService.findGame(req.params(":gameid"));
			if (game == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}
			res.status(200);
			return game;
		} , json());

		// ===========================================================
		// URI: /games/:gameid/players
		// ===========================================================
		// returns all joined players
		get("/games/:gameid/players", (req, res) -> {
			// prüfe ob spiel exiestiert
			Game game = gameService.findGame(req.params(":gameid"));
			if (game == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}
			res.status(200);
			return game.getPlayers();
		} , json());

		// ===========================================================
		// URI: /games/:gameid/players/playerid
		// ===========================================================
		// Gets a {gameid}players
		get("/games/:gameid/players/:playerid", (req, res) -> {
			// prüfe ob spiel exiestiert
			Game game = gameService.findGame(req.params(":gameid"));
			if (game == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			Player player = gameService.getPlayer(req.params(":gameid"), req.params("playerid"));
			if (player == null) {
				res.status(404);
				return new ResponseError(":( player not exist");
			}
			res.status(200);
			return player;
		} , json());

		// Spieler registrieren
		put("/games/:gameid/players/:playerid", (req, res) -> {
			// prüfe ob spiel exiestiert
			Game game = gameService.findGame(req.params(":gameid"));
			if (game == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			// spiel is started?
			if (game.readyToStart()) {
				res.status(400);
				return new ResponseError(":( game is started");
			}

			// prüfe ob spieler mit gleiche ID schon registriert ist
			game = gameService.addPlayer(req.params(":gameid"), req.params(":playerid"));
			if (game == null) {
				res.status(400);
				return new ResponseError(":( player with same ID exist");
			}

			// wurde geklappt
			return game;
		} , json());

		// Removes the player from the game
		delete("/games/:gameid/players/:playerid", (req, res) -> {
			// prüfe ob spiel exiestiert
			Game game = gameService.findGame(req.params(":gameid"));
			if (game == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			// player exist?
			Player player = gameService.getPlayer(req.params(":gameid"), req.params("playerid"));
			if (player == null) {
				res.status(404);
				return new ResponseError(":( player not exist, wrong player ID");
			}

			return gameService.deletePlayer(req.params(":gameid"), req.params("playerid"));
		} , json());

		// ===========================================================
		// URI: /games/:gameid/players/:playerid/ready
		// ===========================================================
		// Benutzer können mit dem Client melden, dass sie fertig sind und das
		// Spiel losgehen kann
		put("/games/:gameid/players/:playerid/ready", (req, res) -> {
			// prüfe ob spiel exiestiert
			Game game = gameService.findGame(req.params(":gameid"));
			if (game == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			// prüfe ob spieler mit eingegebene ID existiert
			Player player = gameService.setPlayerReady(req.params(":gameid"), req.params(":playerid"));
			if (player == null) {
				res.status(400);
				return new ResponseError(":( no such player");
			}

			player.setReady();

			// ToDo:
			// Wenn alle Clients bereit sind, kann das Spiel beginnen – die
			// erste Person muss anfangen zu würfeln!
			// Achten Sie darauf, dass für die verschiedenen Spielkomponenten
			// auch unterschiedliche Hosts
			// über- bzw. angegeben werden können

			return player;
		} , json());

		// tells if the player is ready to start the game
		get("/games/:gameid/players/:playerid/ready", (req, res) -> {
			// prüfe ob spiel exiestiert
			Game game = gameService.findGame(req.params(":gameid"));
			if (game == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			// prüfe ob spieler mit eingegebene ID existiert
			Player player = gameService.getPlayer(req.params(":gameid"), req.params(":playerid"));
			if (player == null) {
				res.status(400);
				return new ResponseError(":( no such player");
			}

			res.status(200);
			return player.getReady();
		} , json());

		// ===========================================================
		// URl: /games/:gameid/players/current
		// ===========================================================
		// gets the currently active player that shall take action
		get("/games/:gameid/players/current", (req, res) -> {
			// prüfe ob spiel exiestiert
			Game game = gameService.findGame(req.params(":gameid"));
			if (game == null) {
				res.status(400);
				return new ResponseError(":( wrong gameId");
			}

			// ToDo
			return null;
		} , json());

		// ===========================================================
		// URl: /games/:gameid/players/turn
		// ===========================================================
		// ToDo
		// GET: gets the player holding the turn mutex 
		// Response 
		// Http Status code 200
		// Http Status code 404 Resource could not be found
		
		// PUT: tries to aquire the turn mutex - Request: Query parameters:
		// player (string ), the playerid of the player trying to aquire the lock
		// Response
		// Http Status code 200 already holding the mutex
		// Http Status code 201 aquired the mutex
		// Http Status code 409 already aquired by an other player
		
		// DELETE: releases the mutex
	}
}