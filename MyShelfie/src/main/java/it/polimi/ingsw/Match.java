package it.polimi.ingsw;

import java.util.Scanner;
import java.util.Random;

public class Match {
    //TODO: implementation coming soon
    Match(int numPlayers) {

        //The board and common goal cards are created and printed
        Board board = new Board(numPlayers);
        ItemEnum.generateCharMatrix(board.getMatrix(), Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addHeaders(Board.BOARD_SIZE).printMatrix();
        board.CommonCards[0].printCommonCard();
        board.CommonCards[0].printCommonCard();

        //An array of players is created containing all the information for each one (Bookshelf, Personal Cards, ID and so on...)
        switch (numPlayers) {
            case 2: create2Players();
                    break;
            case 3: create3Players();
                    break;
            case 4: create4Players();
                    break;
        }
    }

    public void begin(int numPlayers){
        Scanner getMove = new Scanner(System.in);
        Random random = new Random();
        int firstPlayerNumber;

        //assign to each player a personal card
        //TODO: implementation

        //it's decided who will go first
        firstPlayerNumber = random.nextInt(1, numPlayers);

        //the game starts
        //TODO: implementation
    }

    private Player[] create2Players() {
        Player[] playersArray = new Player[2];
        Player player1 = new Player(/*nome giocatore ecc.*/);
        Player player2 = new Player();

        playersArray[0] = player1;
        playersArray[1] = player2;

        return playersArray;
    }
    private Player[] create3Players() {
        Player[] playersArray = new Player[3];
        Player player1 = new Player(/*nome giocatore ecc.*/);
        Player player2 = new Player();
        Player player3 = new Player();

        playersArray[0] = player1;
        playersArray[1] = player2;
        playersArray[2] = player3;

        return playersArray;
    }

    private Player[] create4Players() {
        Player[] playersArray = new Player[4];
        Player player1 = new Player(/*nome giocatore ecc.*/);
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        playersArray[0] = player1;
        playersArray[1] = player2;
        playersArray[2] = player3;
        playersArray[3] = player4;

        return playersArray;
    }
}
