package Bank.model;

import Player.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 schema:
     - bank: |
     {
         "type":    "object",
         "$schema": "http://json-schema.org/draft-03/schema",
         "id":      "bank",
         "properties": {
         }
     }
 */
public class Bank {
    private String bankID;
    private ArrayList<Transfer> transfers = new ArrayList<>();
    private HashMap<String, Account> accounts = new LinkedHashMap<>();

    public Bank(String bankID) {
        this.bankID = bankID;

    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void addAccounts(ArrayList<Player> players) {
        for (Player player : players) {
            Account account = new Account();
            accounts.put(player.getPlayerID(), account);
        }

    }

    public ArrayList<Transfer> getTransfers() {

        return transfers;
    }

    public void addTransfer(Transfer t) {
        this.transfers.add(t);
    }

    public String getBankID() {
        return bankID;
    }
}
