package Dice.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import Boards.modell.Board;

/**
 * Created by diana on 01.11.15.
 */
public class Dice {

    private Roll roll1;
    private Roll roll2;

    public Dice() {
		this.roll1 = new Roll( new Random().nextInt(6) + 1);
		this.roll2 = new Roll( new Random().nextInt(6) + 1);
	}
}
