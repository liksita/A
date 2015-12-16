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
    private ArrayList<Account> accounts = new ArrayList<>();
    private int saldo;

    public Bank(String bankID) {
        this.bankID = bankID;
        this.saldo = 20000000;
    }
    

	public int getSaldo() {
		return saldo;
	}

	public void addSaldo(int saldo) {
		this.saldo = this.saldo + saldo;
	}
	
	public void takeSaldo(int saldo) {
		this.saldo = this.saldo - saldo;
	}

	public void setBankID(String bankID) {
		this.bankID = bankID;
	}

	public void setTransfers(ArrayList<Transfer> transfers) {
		this.transfers = transfers;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccounts(ArrayList<String> players) {
        for (String player : players) {
            Account account = new Account(player);
            accounts.add(account);
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
