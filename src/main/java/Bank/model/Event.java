package Bank.model;

import Player.model.Player;

/**
 - event:  |
     {
         "type":    "object",
         "$schema": "http://json-schema.org/draft-03/schema",
         "id":      "event",
         "properties": {
             "type":    { "type": "string", "required": true  },
             "name":    { "type": "string", "required": true  },
             "reason":  { "type": "string", "required": true  },
             "resource":{ "type": "string", "description": "the uri of the resource related to this event" },
             "player":  { "$ref": "player", "description": "The player issued this event" }
         }
     }
 */
public class Event {
    private String eventID;
    private String name;
    private String reason;
    private String resource;
    private Player player;

}
