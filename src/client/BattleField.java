package client;

import java.util.TreeMap;

public abstract class BattleField {

    //PROPERTIES

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static TreeMap<String, String> grid;


    //CONSTRUCTOR
    public BattleField(){
        this.grid = new TreeMap<>();


    }

    public void createField() {

        String alphabet = "ABCDEFGHIJ";
        String line = "";

        System.out.print(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print("|" + " " + i + " ");
        }
        System.out.print("|");
        System.out.println();

        for (int i = 0; i < 10; i++) {
            String letter = Character.toString(alphabet.charAt(i));
            for (int j = 0; j < 10; j++) {
                grid.put(letter + j, "≈");
                line += grid.get(letter + j + " | ");
            }
            System.out.println(letter + "| " + line);
            line = "";
        }
    }

}
