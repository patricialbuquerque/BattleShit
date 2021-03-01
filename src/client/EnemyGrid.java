package client;

import util.Color;

import java.util.TreeMap;

public class EnemyGrid {

//PROPERTIES

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static TreeMap<String, String> grid;
    private String alphabet = "ABCDEFGHIJ";
    private String line = "";

    //CONSTRUCTOR
    public EnemyGrid(){
        this.grid = new TreeMap<>();
    }

    public void createField() {
        for (int i = 0; i < 10; i++) {
            String letter = Character.toString(alphabet.charAt(i));
            for (int j = 0; j < 10; j++) {
                grid.put(letter + j, " ");
            }
        }
    }


    public void showEnemyGrid(){
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

        grid.put(key, " ");
        System.out.println(key);
    }


    public void shoot(String key){
        if( grid.get(key) == " "){
            grid.put(key," ");
        }
        if( grid.get(key) == " "){
            grid.put(key," ");
        }
    }

    public TreeMap<String, String> getGrid() {
        return grid;
    }

    public void setGrid(TreeMap<String, String> grid) {
        EnemyGrid.grid = grid;
    }

    public void setValue(String key, String value){
        grid.put(key,value);

    }





}
