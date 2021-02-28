package client;

import java.util.HashMap;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {


        BattleField battleField = new BattleField();
        battleField.createField();

        System.out.println(battleField.getGrid().get("A5").equals("≈"));

        battleField.placeShipOnGrid("A3");

        battleField.showBattleField();








    }
}
