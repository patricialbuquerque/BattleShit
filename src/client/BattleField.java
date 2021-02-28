package client;

import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import util.Color;
import util.Symbols;

import java.util.TreeMap;

public class BattleField {

    //PROPERTIES

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static TreeMap<String, String> grid;
    private String alphabet = "ABCDEFGHIJ";
    private String line = "";

    //CONSTRUCTOR
    public BattleField(){
        this.grid = new TreeMap<>();
    }

    public void createField() {
        for (int i = 0; i < 10; i++) {
            String letter = Character.toString(alphabet.charAt(i));
            for (int j = 0; j < 10; j++) {
                grid.put(letter + j,Symbols.SEA);
            }
        }
    }


    public void showBattleField(){
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.print("|" + " " + i + " ");

        }
        System.out.print("|\n");
        for (int i = 0; i < 10; i++) {
            String letter = Character.toString(alphabet.charAt(i));
            for (int j = 0; j < 10; j++) {
                if(j == 0){
                    line += " " + Color.COLOR + "| " + grid.get(letter + j) + Color.COLOR   + Color.RESET;
                }
                if(j > 0 && j < 9){
                    line += Color.COLOR + " | " + grid.get(letter + j)  + Color.RESET;
                }
                if(j == 9){
                    line += Color.COLOR + " | " + grid.get(letter + j) +  Color.COLOR + " |" + Color.RESET;
                }
            }
            System.out.println((letter + line) + (Color.COLOR  + Color.RESET ));
            line = "";
        }
    }

    public void placeShipOnGrid(String key){
        //get shipPosition from inputShipPosition
        grid.put(key, Symbols.SHIP);
    }


    public void shoot(String key){

        if( grid.get(key) == Symbols.SEA){
            grid.put(key,Symbols.SEA_SHOT);
        }
        if( grid.get(key) == Symbols.SHIP){
            grid.put(key,Symbols.SHIP_SHOT);
        }
    }

    public TreeMap<String, String> getGrid() {
        return grid;
    }

    public void setGrid(TreeMap<String, String> grid) {
        BattleField.grid = grid;
    }
}
