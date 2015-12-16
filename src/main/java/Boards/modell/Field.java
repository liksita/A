package Boards.modell;

import Player.model.Player;

import java.util.ArrayList;

public class Field {
    private Place place;
    private ArrayList<Player> players = new ArrayList<>();

    public Field(Place place, ArrayList<Player> players) {
        this.place = place;
        this.players = players;
    }

    public Place getPlace() {
        return place;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
