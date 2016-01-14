package Game;

import Game.controller.GameController;
import Game.service.GameService;


public class GameApplication {

    public static void main(String[] args){

    	
    	
        new GameController(new GameService());
    }
}
