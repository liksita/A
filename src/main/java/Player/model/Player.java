package Player.model;

import Boards.modell.Place;

public class Player {
    String playerID;
    String name;
    String uri;
    Place plaсe;
    int position;
    boolean ready = false;

    public Player(String playerID, String name, String uri, Place plaсe, int position) {
        this.playerID   = playerID;
        this.name       = name;
        this.uri        = uri;
        this.plaсe      = plaсe;
        this.position   = position;
    }

    public Player(String name) {
        this.name       = name;
        this.playerID   = name.toLowerCase();
        this.uri        = "http://localhost:4567/player/" + name.toLowerCase();
    }

    // ===========================
    // Getter's
    // ===========================
    public String getPlayerID() {
        return playerID;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public Place getPlaсe() {
        return plaсe;
    }

    public int getPosition() {
        return position;
    }

    public boolean getReady() {
        return this.ready;
    }

    // ===========================
    // Setter's
    // ===========================
    public void setPlaсe(Place plaсe) {
        this.plaсe = plaсe;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setReady() {
        this.ready = true;
    }

    // ===========================
    @Override
    public boolean equals(Object obj) {

        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        if (this == obj)
            return true;

        Player other = (Player) obj;
        return (playerID.equals(other.getPlayerID())
                && name.equals(other.getName())
                && uri.equals(other.getUri()));
    }
}