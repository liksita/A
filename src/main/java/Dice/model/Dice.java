package Dice.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import Boards.modell.Board;

/**
 * Created by diana on 01.11.15.
 */
public class Dice {

    private int nummer;

    public Dice() {
		this.nummer = new Random().nextInt(6) + 1;
	}

}
