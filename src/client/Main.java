package client;

import java.util.HashMap;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {


        BattleField battleField = new BattleField();
        battleField.createField();
        battleField.changeBattleField("A0");
        battleField.changeBattleField("D4");
        battleField.changeBattleField("H5");

        battleField.showBattleField();





    }
}
