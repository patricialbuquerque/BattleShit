package client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import util.Color;

import java.util.Arrays;

public class Menus {

    //PROPRIETIES
    private Prompt prompt;
    private String playerNewName;
    private boolean canStartGame;
    private Game newGame;
    private BattleField battleField;
    private Color colors;

    //CONSTRUCTOR
    public Menus() {
        prompt = new Prompt(System.in, System.out);
        newGame = new Game();
        playerNewName = "";
        battleField = new BattleField();
        battleField.createField();
        colors = new Color();

    }

    //METHODS
    public void mainMenu(){
        String[] menuOptions = {colors.ANSI_GREEN + "Play Game" + colors.RESET, colors.ANSI_BLUE + "Options" + colors.RESET, colors.ANSI_RED + "Quit Game" + colors.RESET};
        String message = colors.BOLD + "\033[38;5;22m" + "\033[48;5;76m" + "Battle Shits, our weekend surprise!" + colors.RESET;

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
        String[] options = {colors.ANSI_BLUE + "Name: " + colors.RESET + colors.ANSI_YELLOW + playerNewName + colors.RESET, colors.ANSI_BLUE + "Ships Position:" + colors.RESET + colors.ANSI_WHITE + " Choose by player" + colors.RESET, colors.ANSI_BLUE + "Choose Ship Position" + colors.RESET, colors.ANSI_GREEN + "Start Game" + colors.RESET};
        String setMessage = colors.BOLD + colors.ANSI_RED_BACKGROUND + colors.ANSI_BLACK + "Enemy Fleet Ahead! Prepare to Battle!" + colors.RESET;

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
                newGame.start();
        }
    }

    private void preGameMenuTrue(){ //Pregame menu (random ships position = true)
        String[] options = {colors.ANSI_BLUE + "Name: " + colors.RESET + colors.ANSI_YELLOW + playerNewName + colors.RESET, colors.ANSI_BLUE + "Ships Position: " + colors.RESET + colors.ANSI_YELLOW + "RANDOM" + colors.RESET, colors.ANSI_YELLOW + "LOCKED" + colors.RESET, colors.ANSI_GREEN + "Start Game" + colors.RESET};
        String setMessage = colors.BOLD + colors.ANSI_RED_BACKGROUND + colors.ANSI_BLACK + "Enemy Fleet Ahead! Prepare to Battle!" + colors.RESET;

        switch (menuMaker(options, setMessage)) {
            case 1:
                inputPlayerName();
                preGameMenuTrue();
                break;
            case 2:
                preGameMenuFalse();
                break;
            case 3:
                System.out.println(colors.ANSI_RED + "You can't choose this option." + colors.RESET);
                preGameMenuTrue();
                break;
            case 4:
                // inserir condições se escolheu o nome e se escolheu a posição dos barcos.
                newGame.start();
        }
    }

    private void deployPositionShips(){
        Ships.ShipType[] ships = Ships.ShipType.values();

        for(int i = 1; i <= ships.length; i++){

            battleField.showBattleField();

            StringInputScanner question1 = new StringInputScanner();
            question1.setMessage(colors.ANSI_BLUE + "Choose the initial coordinate for " + colors.ANSI_BLUE + colors.ANSI_GREEN + ships[i - 1].toString() + colors.RESET);
            String coordinates = prompt.getUserInput(question1);


            String[] directions = {colors.ANSI_YELLOW + "Horizontal" + colors.RESET, colors.ANSI_YELLOW + "Vertical" + colors.RESET};
            boolean horizontal = menuMaker(directions, colors.BOLD + colors.ANSI_RED + "Choose direction" + colors.RESET) == 1;

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

    public void inGameMenuOpponentTurn(){
        String[] options = {colors.ANSI_BLUE + "Waiting for Opponent" + colors.RESET, colors.ANSI_BLUE + "Chat" + colors.RESET, colors.ANSI_RED + "Rage Quit" + colors.RESET};
        String setMessage = "Incoming Fire! Protect yourself!";
        switch (menuMaker(options,setMessage)){
            case 1:
                System.out.println(colors.ANSI_BLUE + "It's our opponent turn, Admiral " + colors.RESET + colors.ANSI_RED + playerNewName + colors.RESET + colors.ANSI_BLUE + "!" + colors.RESET);
                inGameMenuOpponentTurn();
                break;
            case 2:
                // Implementar o chat.
                System.out.println("Ainda em construção");
            case 3:
                // Enviar mensagem ao oponente a informar que este disconectou!
                System.exit(0);
        }
    }

    public void inGameMenuOurTurn(){
        String[] options = {colors.ANSI_YELLOW + "FIRE!!" + colors.RESET, colors.ANSI_BLUE + "Chat" + colors.RESET, colors.ANSI_RED + "Rage Quit" + colors.RESET};
        String setMessage = colors.ANSI_RED + "Open Fire! Let's shoot the bastards!!" + colors.RESET;
        switch(menuMaker(options,setMessage)){
            case 1:
                // Implementar o disparar sobre inimigo.
            case 2:
                //implementar o chat
            case 3:
                // Enviar mensagem ao oponente a informar que este disconectou!
                System.exit(0);
        }
    }

    public void finalGameMenu(){
        String[] options = {colors.ANSI_GREEN + "Play again" + colors.RESET, colors.ANSI_RED + "Quit Game" + colors.RESET};
        String setMessage = colors.ANSI_BLUE + "Admiral " + colors.RESET + colors.ANSI_YELLOW +  playerNewName + colors.RESET + colors.ANSI_BLUE + ", do you want a rematch?" + colors.RESET;
        switch (menuMaker(options, setMessage)) {
            case 1:
                // dizer ao oponente que está pronto para jogo novo
                // se oponente também quiser rematch mandar para o menu pregamemenufalse
                break;
            case 2:
                // Enviar mensagem ao oponente a informar que este disconectou!
                System.exit(0);
        }
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
        question.setMessage(colors.ANSI_BLUE + "Whats your name Admiral? " + colors.RESET);
        playerNewName = prompt.getUserInput(question);
    }


}