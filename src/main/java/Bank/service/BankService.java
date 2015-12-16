package Bank.service;

import Bank.model.Account;
import Bank.model.Bank;
import Bank.model.Service;
import Bank.model.Transfer;
import Player.model.Player;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import static util.JsonUtil.readUrl;

/**
 * Created by m on 24.11.15.
 */
public class BankService {

	private ArrayList<Bank> banks = new ArrayList<>();

	private static Service service = new Service("Games", "Game Service", "games",
			"https://vs-docker.informatik.haw-hamburg.de/ports/14470/games");

	private static String serviceRegistrationUri = "http://vs-docker.informatik.haw-hamburg.de:8053/services";

	public void register() {
		Gson gson = new Gson();

		try {
			HttpResponse<JsonNode> response = Unirest.post(serviceRegistrationUri).header("accept", "application/json")
					.header("content-type", "application/json").body(gson.toJson(service)).asJson();

			System.out.println("Status: " + response.getStatus() + " Body:" + response.getBody().toString());
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

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

	// public HashMap<String, Account> getAccounts() {
	// return accounts;
	// }

	public Transfer findTransfers(String gameID, String transferID) {

		for (Transfer transfer : getTransfers(gameID)) {
			if (transfer.getID().equals(transferID))
				return transfer;
		}
		return null;
	}

	public Transfer transferFromTo(String gameID, String from, String to, String amount, String reason) {
		Bank bank = findBank(gameID);
		int amountValue = Integer.parseInt(amount);
		// player from, to test
		Transfer transfer = new Transfer(from, to, amountValue, reason, "event");
		for (Account a : bank.getAccounts()) {
			if (a.getPlayer().equals(from)) {
				a.takeSaldo(amountValue);
			}
			if (a.getPlayer().equals(to)) {
				a.addSaldo(amountValue);
			}
		}
		bank.addTransfer(transfer);
		return transfer;
	}

	public Transfer transferFrom(String gameID, String to, String amount, String reason) {
		Bank bank = findBank(gameID);
		int amountValue = Integer.parseInt(amount);
		// player from, to test
		Transfer transfer = new Transfer(bank.getBankID(), to, amountValue, reason, "event");
		for (Account a : bank.getAccounts()) {
			bank.takeSaldo(amountValue);
			if (a.getPlayer().equals(to)) {
				a.addSaldo(amountValue);
			}
		}
		bank.addTransfer(transfer);
		return transfer;
	}

	public Transfer transferTo(String gameID, String from, String amount, String reason) {
		Bank bank = findBank(gameID);
		int amountValue = Integer.parseInt(amount);
		// player from, to test
		Transfer transfer = new Transfer(from, bank.getBankID(), amountValue, reason, "event");
		for (Account a : bank.getAccounts()) {
			bank.addSaldo(amountValue);
			if (a.getPlayer().equals(from)) {
				a.takeSaldo(amountValue);
			}
		}
		bank.addTransfer(transfer);
		return transfer;
	}

	public JSONArray getPlayers(String gameID) throws UnirestException {
		Bank bank = findBank(gameID);
		HttpResponse<JsonNode> playerResponse = Unirest
				.get("http://localhost:4567/games/" + gameID + "/players/").asJson();
		JsonNode playerNode = playerResponse.getBody();
		JSONArray playerObj = playerNode.getArray();
		return playerObj;
	}

	public int getPlayersSaldo(String gameID, String playerID) throws UnirestException {
		Bank bank = findBank(gameID);
		HttpResponse<JsonNode> playerResponse = Unirest
				.get("http://localhost:4567/games/" + gameID + "/players/" + playerID).asJson();
		JsonNode playerNode = playerResponse.getBody();
		JSONObject playerObj = playerNode.getObject();
		String player = playerObj.getString("player");
		for (Account a : bank.getAccounts()) {
			if (a.getPlayer().equals(player)) {
				return a.getSaldo();
			}
		}
		return 0;
	}
	
	public boolean createAccounts(String gameID) throws UnirestException {
		Bank bank = findBank(gameID);
		HttpResponse<JsonNode> playerResponse = Unirest
				.get("http://localhost:4567/games/" + gameID + "/players/").asJson();
		JsonNode playerNode = playerResponse.getBody();
		JSONObject playerObj = playerNode.getObject();
		JSONArray players = playerObj.getJSONArray("player");
		for (int i = 0; i < players.length() + 1; i++){
			Account a = new Account(players.getString(i));
				 bank.getAccounts().add(a);
				 return true;
				 
			}
		return false;
	}
}
