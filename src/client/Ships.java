package client;

import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

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


    public Ships(ShipType type, String initialPosition, boolean horizontal) {
        this.type = type;
        this.initialPosition = initialPosition;
        this.horizontal = horizontal;

        switch(type) {
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
    public Ships(ShipType type){
        this.type = type;

    }

    public void validateShipPosition(BattleField battleField){

        int initialRow = Character.getNumericValue(initialPosition.charAt(0));
        int initialCol = Character.getNumericValue(initialPosition.charAt(1));


        //if horizontal, needs to increase the number of the key
        if(horizontal == true){
            for(int i = 0; i < size; i++){
                if (battleField.getGrid().get(initialPosition) == "≈"){


            }
        }        }

    }









    /*public void inputShipPosition(Ships.ShipType ship){

        StringInputScanner question1 = new StringInputScanner();
        question1.setMessage("Choose " + ship + " position.");
        String shipPosition = prompt.getUserInput(question1);


        String[] options = {"Vertical","Horizontal"};
        menuMaker(options, "Vertical or Horizontal?");
        //int shipVertHor = prompt.getUserInput(options);
        // 1 vertical 2 horizontal
        //if((grid.get(question1) == "≈") && ){


       /* }
        for( int i = 0; i < ship.size; i++){
            if( shiptVertHor == 1){

            }*/




}
