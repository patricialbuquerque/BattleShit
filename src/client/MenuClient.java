package client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import server.GameServer;

public class MenuClient {

    //PROPRIETIES
    private Prompt prompt;
    private String playerNewName;
    //private int answerIndex;



    //CONSTRUCTOR
    public MenuClient() {
        prompt = new Prompt(System.in, System.out);
        playerNewName = "INSERT NEW NAME";
        //answerIndex = -1;
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

    public void preGameMenuFalse(){ // Pregame menu (random ships position = false)
        String[] options = {"Name: " + playerNewName, "Ships Position: Choose by player", "Choose Ship Position", "Start Game"};
        String setMessage = "Enemy Fleet Ahead! Prepare to Battle!";

        switch (menuMaker(options, setMessage)) {
            case 1:
                inputText("Choose your name: ");
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
                GameServer newGame = new GameServer();
                newGame.start();
        }
    }

    public void preGameMenuTrue(){ //Pregame menu (random ships position = true)
        String[] options = {"Name: " + playerNewName, "Ships Position: Random", "LOCKED", "Start Game"};
        String setMessage = "Enemy Fleet Ahead! Prepare to Battle!";

        switch (menuMaker(options, setMessage)) {
            case 1:
                inputText("Choose your name: ");
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
                // inserir a condição se escolheu o nome.
                GameServer newGame = new GameServer();
                newGame.start();
        }
    }

    public void deployPositionShips(){ //Responsable for positioning our ships in the grid.




    }

    public void inGameMenuOpponentTurn(){
        String[] options = {"Waiting for Opponent", "Chat", "Rage Quit"};
        String setMessage = "Incoming Fire! Protect yourself!";
        switch (menuMaker(options,setMessage)){
            case 1:
                System.out.println("It's our opponent turn, Admiral " + playerNewName + "!");
                inGameMenuOurTurn();
                break;
            case 2:
                // Implementar o chat.
                System.out.println("Ainda em construção");
            case 3:
                // Enviar mensagem ao oponente que este disconectou!
                System.exit(0);
        }
    }

    public void inGameMenuOurTurn(){
        String[] options = {"FIRE!!", "Chat", "Rage Quit"};
        String setMessage = "Open Fire! Let's shoot the bastards!!";
        switch(menuMaker(options,setMessage)){
            case 1:
                // Implementar o disparar sobre inimigo.
            case 2:
                //implementar o chat
            case 3:
                // Enviar mensagem ao oponente que este disconectou!
                System.exit(0);
        }
    }

    public void finalGameMenu(){


    }

    public void optionsMenu(){
        System.out.println("Site em construção...");
    }

    private int menuMaker(String[] menuOptions, String setMessageToDisplay){
        MenuInputScanner scanner = new MenuInputScanner(menuOptions);
        scanner.setMessage(setMessageToDisplay);
        return prompt.getUserInput(scanner);
    }

    private void inputText(String setMessage){
        StringInputScanner question = new StringInputScanner();
        question.setMessage(setMessage);
        playerNewName = prompt.getUserInput(question);
    }


}
