package it.polimi.ingsw.view.CLI;

import it.polimi.ingsw.ItemEnum;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Board;

import java.io.PrintStream;
import java.util.Scanner;

public class CliView implements Runnable {
    private Scanner scanner;
    private PrintStream outputStream;
    private boolean done;
    private Controller controllerCli;

//it is temporary; later I will create an interface similar to the observers;

    public CliView(int numPlayers){
        scanner = new Scanner(System.in);
        outputStream = new PrintStream(System.out);
        controllerCli = new Controller(numPlayers);
    }

    public void match(){
        int x, i;
        String name;
        x = controllerCli.getNumPlayers();
        for(i=0; i<x; i++){
            outputStream.println("Player" + i + ", insert your username");
            do{
                name = scanner.next();
                if(controllerCli.duplicatedUsername(name)){
                    outputStream.println("Select another username, this has been already selected.");
                }
            }while(controllerCli.duplicatedUsername(name));
            controllerCli.setUsernamePlayer(name);
        }
        controllerCli.setFirstPlayer();
        //TODO: print the board
        while (true) {
            outputStream.println("How many tiles do you want to pick?");
            i = scanner.nextInt();
            //control the space in the bookshelf;
            while(controllerCli.enoughSpaceBookshelf(i)){
                outputStream.println("You don't have enough space in your bookshelf");
                i = scanner.nextInt();
            }
            outputStream.println("please enter the tiles' coordinates you want to remove in the order you want to put them in the bookshelf, from bottom to top.");
            outputStream.println("example: x1,y1");
            //TODO: complete the selection and insertion of the tiles!
            switch (i){
                case 1:
                    input1Tile();
                    break;
                case 2:
                    input2Tiles();
                    break;
                case 3:
                    input3Tiles();
                    break;
            }
        }
    }

    //TODO: complete this method
    private boolean input1Tile(){
        String s = scanner.next();
        String[] inputs = s.split(",");
        return controllerCli.isFeasibleMove(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
    }

    private void input2Tiles(){
        String s = scanner.next();
    }

    private void input3Tiles(){
        String s = scanner.next();
    }

    @Override
    public void run() {
        while(!done){
            match();
        }
    }
}