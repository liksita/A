package Bank.model;


/**
 - transfer: |
     {
         "type":    "object",
         "$schema": "http://json-schema.org/draft-03/schema",
         "id":      "transfer",
         "properties": {
             "from":    { "type": "string", "description":"{playerid} or bank", "required": true  },
             "to":      { "type": "string", "description":"{playerid} or bank", "required": true  },
             "reason":  { "type": "string", "required": true  },
             "event":   { "type": "string", "required": false  }
         }
     }
 */
public class Transfer {
    private String transferID;
    private String from;
    private String to;
    private int amount;
    private String reason;
    private String event;

    public Transfer(String from, String to, int amount, String reason, String event) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.reason = reason;
        this.event = event;
    }

    public String getTransferID() {
        return transferID;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getReason() {
        return reason;
    }

    public String getEvent() {
        return event;
    }

    public String getID() {
        return transferID;
    }
}
