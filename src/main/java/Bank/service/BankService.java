package Bank.service;

import Bank.model.Bank;
import Bank.model.Transfer;
import Player.model.Player;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;

import static util.JsonUtil.readUrl;

/**
 * Created by m on 24.11.15.
 */
public class BankService {
    private ArrayList<Bank> banks = new ArrayList<>();


    public Bank findBank(String gameID) {
        for (Bank bank : banks) {
            if (bank.getBankID().equals(gameID)) {
                return bank;
            }
        }
        return null;
    }

    public ArrayList<Transfer> getTransfers(String gameID) {
        Bank bank = findBank(gameID);
        return bank.getTransfers();
    }

    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public Bank createBank(String gameID) {

        Bank bank = new Bank(gameID);
        banks.add(bank);
        return bank;
    }

//    public HashMap<String, Account> getAccounts() {
//        return accounts;
//    }

    public Transfer findTransfers(String gameID, String transferID) {

        for (Transfer transfer : getTransfers(gameID)) {
            if (transfer.getID().equals(transferID)) return transfer;
        }
        return null;
    }

    public Transfer transferFromTo(String gameID, String from, String to, String amount, String reason) {
        Bank bank = findBank(gameID);
        int amountValue = Integer.parseInt(amount);
        //player from, to test
        Transfer transfer = new Transfer(from, to, amountValue, reason, "event");
        bank.addTransfer(transfer);
        return transfer;
    }

    public HttpResponse<JsonNode> getPlayers(String gameID) {
        HttpResponse<JsonNode> players = null;
        try {
            players = Unirest.get("http://localhost:4567/games/" + gameID + "/players").asJson();
////           String gameGson = readUrl("http://0.0.0.0:4567/games/" + gameID);
//            final Game game = gson.fromJson(gameGson, Game.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        if (players != null) {
            return players;
        }
        return null;
    }

    public ArrayList<Player> getPlayersString(String gameID) throws Exception {

//        GetRequest players = Unirest.get("http://localhost:4567/games" + "/" + gameID + "/players");
////           String gameGson = readUrl("http://0.0.0.0:4567/games/" + gameID);
//            final Game game = gson.fromJson(gameGson, Game.class);
        String playersGson = readUrl("http://0.0.0.0:4567/games/" + gameID + "/players");
        Gson gson = new Gson();
        ArrayList<Player> players = gson.fromJson(playersGson, ArrayList.class);
        return  players;

    }
}
