package client;

import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import util.Symbols;

public class Ships {

    public enum ShipType {
        BATTLESHIP,
        CRUISER,
        FRIGATE,
        MINESWEEPER
    }

    private ShipType type;
    private int size;
    private String initialPosition;
    private boolean horizontal;
    private String alphabet = "ABCDEFGHIJ";
    private int numericRow;
    private int numericCol;
    private String initialRow;
    private String initialCol;


    public Ships(ShipType type, String initialPosition, boolean horizontal){
        this.type = type;
        this.initialPosition = initialPosition;
        this.horizontal = horizontal;
        numericRow = Character.getNumericValue(initialPosition.charAt(0)) - 10;
        numericCol = Character.getNumericValue(initialPosition.charAt(1));
        initialRow = initialPosition.substring(0, 1);
        initialCol = initialPosition.substring(1);

        switch (type) {
            case BATTLESHIP:
                size = 5;
                break;
            case CRUISER:
                size = 4;
                break;
            case FRIGATE:
                size = 3;
                break;
            case MINESWEEPER:
                size = 2;
                break;
        }

    }


    //extra constructor - needs to be FINISHED
   /* public Ships(ShipType type){
        this.type = type;
    }*/

    public boolean validateShipPosition(BattleField battleField) {

        //if horizontal, needs to increase the number of the key
        if (horizontal == true) {
            for (int i = numericCol; i < size + numericCol; i++) {
                String key = initialRow + i;

                if (battleField.getGrid().get(key) != Symbols.SEA) {
                    System.out.println("You cannot place ship here...");
                    return false;
                }
            }
            return true;
        } else {

            for (int i = numericRow; i < size + numericRow; i++) {
                String key = Character.toString(alphabet.charAt(i)) + initialCol;

                if (battleField.getGrid().get(key) != Symbols.SEA) {
                    System.out.println("You cannot place ship here...");
                    return false;
                }
            }
            return true;
        }
    }

    public void placeShips(BattleField battleField) {

        //if horizontal, needs to increase the number of the key
        if (horizontal == true) {
            for (int i = numericCol; i < size + numericCol; i++) {

                String key = initialRow + String.valueOf(i);
                battleField.placeShipOnGrid(key);
            }
            return;
        }
        for (int i = numericRow; i < size + numericRow; i++) {

            String key = alphabet.charAt(i) + initialCol;
            battleField.placeShipOnGrid(key);
        }
    }
}

