package client;

public class Ships {

    public enum ShipType {
        BATTLESHIP,
        CRUISER,
        FRIGATE,
        MINESWEEPER
    }
    private ShipType type;
    private int size;


    public Ships(ShipType type) {
        this.type = type;

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

}
