package Game.model;

/**
 * Created by diana on 24.11.15.
 * 
 * "components": 
        {
            "type": "object",
            "$schema": "http://json-schema.org/draft-03/schema",
            "id": "file:///home/zieger/haw/owncloud/VS/2015/Aufgaben/components",
            "description": "gameHostcomponents as full, absolute url",
            "properties": 
            {
                "game": 
                {
                    "type": "string",
                    "required": true
                },
                "dice": 
                {
                    "type": "string",
                    "required": true
                },
                "board": 
                {
                    "type": "string",
                    "required": true
                },
                "bank": 
                {
                    "type": "string",
                    "required": true
                },
                "broker": 
                {
                    "type": "string",
                    "required": true
                },
                "decks": 
                {
                    "type": "string",
                    "required": true
                },
                "events": 
                {
                    "type": "string",
                    "required": true
                }
            }
        }
 */
public class Components {
	
	private String gameHost;
    private String diceHost;
    private String boardHost;
    private String bankHost;
    private String brokerHost;
    private String deckHost;
    private String eventHost;

    public String getGame() {
        return gameHost;
    }

    public void setGame(String gameHost) {
        this.gameHost= gameHost;
    }

    public String getDice() {
        return diceHost;
    }

    public void setDice(String diceHost) {
        this.diceHost= diceHost;
    }

    public String getBoard() {
        return boardHost;
    }

    public void setBoard(String boardHost) {
        this.boardHost= boardHost;
    }

    public String getBank() {
        return bankHost;
    }

    public void setBank(String bankHost) {
        this.bankHost= bankHost;
    }

    public String getBroker() {
        return brokerHost;
    }

    public void setBroker(String brokerHost) {
        this.brokerHost= brokerHost;
    }

    public String getDeck() {
        return deckHost;
    }

    public void setDeck(String deckHost) {
        this.deckHost = deckHost;
    }

    public String getEvent() {
        return eventHost;
    }

    public void setEvent(String eventHost) {
        this.eventHost = eventHost;
    }
}
