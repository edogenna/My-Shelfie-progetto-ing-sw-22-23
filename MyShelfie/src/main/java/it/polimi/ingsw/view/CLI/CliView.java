package it.polimi.ingsw.view.CLI;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.view.UI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class CliView implements Runnable, UI{
    private Scanner scanner;
    private PrintStream outputStream;
    private boolean done;

    public CliView(){
        scanner = new Scanner(System.in);
        outputStream = new PrintStream(System.out);
    }

    public void playerMove(){
        while (true) {
            outputStream.println("How many tiles do you want to pick?");
            int i = scanner.nextInt();
            //control the space in the bookshelf; ...
            outputStream.println("please enter the coordinates of the tiles you want to remove in the order you want to put them in the bookshelf (from bottom to top): ");
            String s = scanner.next();
            try {
                String[] inputs = s.split(",");
                handleMove(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
                break;
            }catch(NumberFormatException e){
                outputStream.println("Please provide integer values as coordinates");
            }
        }
    }

    private void handleMove(int row, int col) {
//        setChanged();
//        notifyObservers(new MovePickCard(row, col));
    }

    @Override
    public void run() {
        while(!done){
            playerMove();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        showBoard((Board)arg);
    }
}