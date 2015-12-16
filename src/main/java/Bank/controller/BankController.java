package Bank.controller;

import Bank.model.Bank;
import Bank.model.Transfer;
import Bank.service.BankService;
import errors.ResponseError;

import static spark.Spark.*;
import static util.JsonUtil.json;



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
        	
//        	bankService.register();

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

        post("banks/:gameid/transfer/from/:from/to/:to/:amount", (req, res) -> {
            String reason = req.body();

            Bank bank = bankService.findBank(req.params(":gameid"));
            if (bank == null) {
                res.status(400);
                return new ResponseError(":( wrong gameID");
            }
            Transfer transfer = bankService.transferFromTo(req.params(":gameid"), req.params(":from"), req.params(":to"), req.params(":amount"), reason);
            if (transfer == null) {
                res.status(400);
                return new ResponseError(":( wrong transferID");
            }
            return transfer;
        }, json());
//
//        //===========================================================
//        // Creates a new bank transfer from the bank itself
//        //===========================================================

        post("banks/:gameid/transfer/to/:to/:amount", (req, res) -> {
            String reason = req.body();

            Bank bank = bankService.findBank(req.params(":gameid"));
            if (bank == null) {
                res.status(400);
                return new ResponseError(":( wrong gameID");
            }
            Transfer transfer = bankService.transferFrom(req.params(":gameid"), req.params(":to"), req.params(":amount"), reason);
            if (transfer == null) {
                res.status(400);
                return new ResponseError(":( wrong transferID");
            }
            return transfer;
        }, json());

        //===========================================================
        // creates a new bank transfer to the bank itself
        //===========================================================
        post("banks/:gameid/transfer/from/:from/:amount", (req, res) -> {
            String reason = req.body();

            Bank bank = bankService.findBank(req.params(":gameid"));
            if (bank == null) {
                res.status(400);
                return new ResponseError(":( wrong gameID");
            }
            Transfer transfer = bankService.transferTo(req.params(":gameid"), req.params(":from"), req.params(":amount"), reason);
            if (transfer == null) {
                res.status(400);
                return new ResponseError(":( wrong transferID");
            }
            return transfer;
        }, json());

        //===========================================================
        // List of available players *
        //===========================================================

        get("banks/:gameid/players", (req, res) -> {
            Bank bank = bankService.findBank(req.params(":gameid"));
            if (bank == null) {
                res.status(400);
                return new ResponseError(":( wrong gameID");
            }
            try {
				return bankService.getPlayers(req.params(":gameid"));
			} catch (Exception e) {

				res.status(404);
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				return e;
			}
        });

//        //===========================================================
//        // Returns the saldo of the player
//        //===========================================================

        get("banks/:gameid/players/:playerid", (req, res) -> {
                    Bank bank = bankService.findBank(req.params(":gameid"));
                    if (bank == null) {
                        res.status(400);
                        return new ResponseError(":( wrong gameID");
                    }

                    try {
						return bankService.getPlayersSaldo(req.params(":gameid"), req.params(":playerid"));
					} catch (Exception e) {
						res.status(404);
						// TODO Auto-generated catch block
						e.printStackTrace();
						return e;
					}
                }, json()
        );

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
                        return bankService.createAccounts(req.params(":gameid"));
                    } catch (Exception e) {
                    	res.status(404);
                        e.printStackTrace();
                        return e;
                    }
                }


        );


    }
}