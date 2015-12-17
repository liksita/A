package Bank.model;

import Player.model.Player;

/**
 - account: |
     {
         "type":    "object",
         "$schema": "http://json-schema.org/draft-03/schema",
         "id":      "account",
         "properties": {
             "player":  {"$ref":"player", "required":"true"},
             "saldo":   {"type":"int", "required":"true"}
         }
     }
 */
public class Account {
    private static int accountID = 0;
    private String player;
    private int saldo;
    
    
    
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public Account(String player) {

		this.accountID = this.accountID + 1;
		this.player = player;
		this.saldo = 15000;
	}






	public static int getAccountID() {
		return accountID;
	}
	public static void setAccountID(int accountID) {
		Account.accountID = accountID;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getSaldo() {
		return this.saldo;
	}
	public void addSaldo(int saldo) {
		this.saldo = this.saldo + saldo;
	}
    
	public void takeSaldo(int saldo) {
		this.saldo = this.saldo - saldo;
	}
    
    
}
