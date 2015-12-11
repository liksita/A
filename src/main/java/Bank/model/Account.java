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
    private String accountID;
    private Player player;
    private int saldo;
}
