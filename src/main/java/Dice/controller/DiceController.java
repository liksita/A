package Dice.controller;
import com.google.gson.Gson;

import Dice.model.*;

import static spark.Spark.get;

import java.util.ArrayList;
/**
 * Created by diana on 01.11.15.
 */
public class DiceController {

    private static Gson gson = new Gson();
    private static Dice dice = new Dice();

    public static void main( String[] args) {
        get("/dice", (req, res) -> {    	
            return  new Dice();
        }, gson::toJson);
    }
}
