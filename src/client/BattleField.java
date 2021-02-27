package client;

import java.util.HashMap;

public abstract class BattleField {

    //PROPERTIES

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static HashMap<String, String> grid;


    //CONSTRUCTOR
    public BattleField(){
        this.grid = new HashMap<>();


    }

    //METHODS

    public void waterGrid(){
        String alphabet = "ABCDEFGHIJ";

        for(int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++) {

                grid.put(alphabet.substring(i , i + 1) + j, "≈");

            }
        }

    }
}
