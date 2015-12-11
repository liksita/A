package Bank.controller;

import Bank.model.Bank;
import Bank.model.Transfer;
import Bank.service.BankService;
import errors.ResponseError;

import static spark.Spark.*;
import static util.JsonUtil.json;


/**
 * /banks/{gameid}:
 * type: { item: { schema: "bank", "example":"" } }
 * put:
 * body:
 * application/json:
 * schema: game
 * /transfers:
 * type: { list: { schema: "transfer", "example":"" } }
 * /{transferid}:
 * get:
 * description: Gets a <<resourcePathName>>
 * responses:
 * 200:
 * body:
 * application/json:
 * schema: transfer
 * example: |
 * {}
 * /transfer/from/{from}/to/{to}/{amount}:
 * post:
 * description: creates a new bank transfer
 * body:
 * application/json:
 * schema: '{"type":"string", "required":true}'
 * example: Rent for Badstrasse
 * responses:
 * 201:
 * description: A new bank transfer has been created
 * body:
 * application/json:
 * schema: events
 * 403:
 * description: insufficient fonds
 * <p>
 * // 3. Zustands abfrage vom Spiel
 * get("/games/:gameid" , (req, res) -> {
 * res.header("Content-Type", "application/json");
 * String gameID = req.params(":gameid");
 * String gameGson = "http://0.0.0.0:4567/games/" + gameID;
 * <p>
 * Game game = gson.fromJson(gameGson, Game.class);
 * <p>
 * return gson.toJson(game.isStarted());
 * });
 */

public class BankController {

    public BankController(final BankService bankService) {

        after((req, res) -> {
            res.type("application/json");
        });

        //===========================================================
        // Get's a list of banks
        //===========================================================

        get("/banks", (req, res) -> bankService.getBanks(), json());

        //===========================================================
        // places a banks
        //===========================================================

        put("/banks/:gameid", (req, res) -> {

            String gameID = req.params(":gameid");

            //if bank already exists

            Bank bank = bankService.findBank(gameID);
            if (bank != null) {
                res.status(400);
                return new ResponseError(":( wrong gameID, such bank already exists");
            }

            // if game exists


//            String gamesStr = Unirest.get("http://localhost:4567/games" + gameID).toString();
//            if (!gamesStr.isEmpty()) {
//                return bankService.createBank(gameID);
//            }
//
//
//            // if there is no such game
//            res.status(400);
//            return new ResponseError(":( wrong gameID");
//        }, json());

            return bankService.createBank(gameID);

        }, json());


        //===========================================================
        // Gets a banks
        //===========================================================
        get("/banks/:gameid", (req, res) -> {
            Bank bank = bankService.findBank(req.params(":gameid"));
            if (bank == null) {
                res.status(400);
                return new ResponseError(":( wrong gameID");
            }
            res.status(200);
            return bank;
        }, json());


        //===========================================================
        // List of available transfers
        //===========================================================
        get("/banks/:gameid/transfers", (req, res) -> bankService.getTransfers(req.params(":gameid")), json());

        //===========================================================
        // Gets details of a bank transfer
        //===========================================================

        get("/banks/:gameid/transfers/:transferid", (req, res) -> {
            Transfer transfer = bankService.findTransfers(req.params(":gameid"), req.params(":transferid"));
            if (transfer == null) {
                res.status(400);
                return new ResponseError(":( wrong transferID");
            }
            return transfer;
        }, json());

        //===========================================================
        // Creates a new bank transfer
        //===========================================================

//        post("banks/:gameid/transfer/from/:from/to/:to/:amount", (req, res) -> {
//            String reason = req.body();
//
//            Bank bank = bankService.findBank(req.params(":gameid"));
//            if (bank == null) {
//                res.status(400);
//                return new ResponseError(":( wrong gameID");
//            }
//            Transfer transfer = bankService.transferFromTo(req.params(":gameid"), req.params(":from"), req.params(":to"), req.params(":amount"), reason);
//            if (transfer == null) {
//                res.status(400);
//                return new ResponseError(":( wrong transferID");
//            }
//            return transfer;
//        }, json());
//
//        //===========================================================
//        // Creates a new bank transfer from the bank itself
//        //===========================================================
//
//        post("banks/:gameid/transfer/to/:to/:amount", (req, res) ->
//                "ToDo", json()
//        );
//
//        //===========================================================
//        // creates a new bank transfer to the bank itself
//        //===========================================================
//        post("banks/:gameid/transfer/from/:from/:amount", (req, res) ->
//                "ToDo", json()
//        );


        //===========================================================
        // List of available players *
        //===========================================================

//        get("banks/:gameid/players", (req, res) -> {
//            Bank bank = bankService.findBank(req.params(":gameid"));
//            if (bank == null) {
//                res.status(400);
//                return new ResponseError(":( wrong gameID");
//            }
//            HttpResponse<JsonNode> players = bankService.getPlayers(req.params(":gameid"));
//            return players;
//        });

//        //===========================================================
//        // Returns the saldo of the player
//        //===========================================================
//
//        get("banks/:gameid/players/:playerid", (req, res) -> {
//                    Bank bank = bankService.findBank(req.params(":gameid"));
//                    if (bank == null) {
//                        res.status(400);
//                        return new ResponseError(":( wrong gameID");
//                    }
//
//                    String r = "ToDo";
//                    return r;
//                }, json()
//        );

        //===========================================================
        // Creates a bank account
        //===========================================================

        get("banks/:gameid/players", (req, res) -> {
                    Bank bank = bankService.findBank(req.params(":gameid"));
                    if (bank == null) {
                        res.status(400);
                        return new ResponseError(":( wrong gameID");
                    }
                    try {
                        return bankService.getPlayersString(req.params(":gameid"));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return e;
                    }
                }

//            bank.addAccount(playersArray);
//
//                    String r = "ToDo";
//                    return r;
//
//                }, json()


        );


    }
}
