package client;

import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import util.Color;

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
                grid.put(letter + j,"≈");
            }
        }
    }


    public void showBattleField(){
        System.out.print(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print("|" + " " + i + " ");
        }
        System.out.print("|\n");
        for (int i = 0; i < 10; i++) {
            String letter = Character.toString(alphabet.charAt(i));
            for (int j = 0; j < 10; j++) {
                line += grid.get(letter + j) + " | ";
            }
            System.out.println(letter + "| " + line);
            line = "";
        }
    }

    public void placeShipOnGrid(String key){
        //get shipPosition from inputShipPosition

        grid.put(key, "#");
        System.out.println(key);
    }
    public void shoot(String key){
        if( grid.get(key) == "≈"){
            grid.put(key,"O");
        }
        if( grid.get(key) == "#"){
            grid.put(key, Color.ANSI_RED + "X" + Color.ANSI_WHITE);
        }
    }

    public TreeMap<String, String> getGrid() {
        return grid;
    }

    public void setGrid(TreeMap<String, String> grid) {
        BattleField.grid = grid;
    }
}
