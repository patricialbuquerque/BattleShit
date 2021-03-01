package client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

public class Menus {

    //PROPRIETIES
    private Prompt prompt;
    private String playerNewName;
    private boolean canStartGame;
    private Communicator newCommunicator;
    private BattleField battleField;
    private EnemyGrid enemyGrid;

    //CONSTRUCTOR
    public Menus() {
        prompt = new Prompt(System.in, System.out);
        newCommunicator = new Communicator(this);
        enemyGrid = new EnemyGrid();
        playerNewName = "";
        battleField = new BattleField();
        battleField.createField();

    }

    //METHODS
    public void mainMenu(){
        String[] menuOptions = {"Play Game", "Options", "Quit Game"};
        String message = "Battle Shits, our weekend surprise!";

        switch (menuMaker(menuOptions, message)){
            case 1:
                preGameMenuFalse(); // Will take us to the pregame menu.
                break;
            case 2:
                optionsMenu(); // Will take us to the options menu.
                break;
            case 3:
                System.exit(0); // Shuts down processes.
        }
    }

    private void preGameMenuFalse(){ // Pregame menu (random ships position = false)
        String[] options = {"Name: " + playerNewName, "Ships Position: Choose by player", "Choose Ship Position", "Start Game"};
        String setMessage = "Enemy Fleet Ahead! Prepare to Battle!";

        switch (menuMaker(options, setMessage)) {
            case 1:
                inputPlayerName();
                preGameMenuFalse();
                break;
            case 2:
                preGameMenuTrue();
                break;
            case 3:
                deployPositionShips();//Implementar depois de termos os barcos definidos. Vai aqui deixar de ser água para ser barco.
                break;
            case 4:
                // inserir condições se escolheu o nome e se escolheu a posição dos barcos.
                newCommunicator.begin();
        }
    }

    private void preGameMenuTrue(){ //Pregame menu (random ships position = true)
        String[] options = {"Name: " + playerNewName, "Ships Position: Random", "LOCKED", "Start Game"};
        String setMessage = "Enemy Fleet Ahead! Prepare to Battle!";

        switch (menuMaker(options, setMessage)) {
            case 1:
                inputPlayerName();
                preGameMenuTrue();
                break;
            case 2:
                preGameMenuFalse();
                break;
            case 3:
                System.out.println("You can't choose this option.");
                preGameMenuTrue();
                break;
            case 4:
                // inserir condições se escolheu o nome e se escolheu a posição dos barcos.
                newCommunicator.begin();
        }
    }

    private void deployPositionShips(){
        Ships.ShipType[] ships = Ships.ShipType.values();

        for(int i = 1; i <= ships.length; i++){

            battleField.showBattleField();

            StringInputScanner question1 = new StringInputScanner();
            question1.setMessage("Choose the initial coordinate for " + ships[i - 1].toString());
            String coordinates = prompt.getUserInput(question1);


            String[] directions = {"Horizontal", "Vertical"};
            boolean horizontal = menuMaker(directions, "Choose direction") == 1;

            Ships newShips = new Ships(ships[i - 1], coordinates, horizontal);

            if(newShips.validateShipPosition(battleField) == false){
               i--;
               continue;
            }
            newShips.placeShips(battleField);


        }
        battleField.showBattleField();
        preGameMenuFalse();
    }


    public void optionsMenu(){
        System.out.println("Site em construção...");
    }

    private int menuMaker(String[] menuOptions, String setMessageToDisplay){
        MenuInputScanner scanner = new MenuInputScanner(menuOptions);
        scanner.setMessage(setMessageToDisplay);
        return prompt.getUserInput(scanner);
    }

    private void inputPlayerName(){
        StringInputScanner question = new StringInputScanner();
        question.setMessage("Whats your name Admiral? ");
        playerNewName = prompt.getUserInput(question);
    }

    public String getbattleFieldValue(String key){
        return battleField.getValue(key);

    }

    public void setBattleFieldValue(String key, String value){
         battleField.setValue(key, value);
    }

    public void setEnemyGridValue(String key, String value){
        enemyGrid.setValue(key,value);
    }

    public void showPlayerGrid(){
        battleField.showBattleField();
    }
    public void showEnemyGrid(){
        enemyGrid.showEnemyGrid();
    }

}