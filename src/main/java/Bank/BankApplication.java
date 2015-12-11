package Bank;

import Bank.controller.BankController;
import Bank.service.BankService;
import Game.controller.GameController;
import Game.service.GameService;

/**
 * Created by diana on 24.11.15.
 */
public class BankApplication {
    public static void main(String[] args) {
        new BankController(new BankService());
        new GameController(new GameService());
    }
}
