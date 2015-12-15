package Dice.model;

import java.util.Random;

/**
 * Created by m on 23.11.15.
 */
public class Roll {
    private String name;
    private Dice dice;

    public Roll(String name) {
        this.name = name;
        this.dice = new Dice();
    }
}
