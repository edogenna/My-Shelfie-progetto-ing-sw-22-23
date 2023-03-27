package it.polimi.ingsw;

import java.util.Scanner;
import java.util.Random;

public class Match {
    private Player[] Players;
    private int numPlayers;
    public Card[] PersonalCards;
    Match(int num) {

        numPlayers=num;
        //The board and common goal cards are created and printed
        Board board = new Board(numPlayers);
        ItemEnum.generateCharMatrix(board.getMatrix(), Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addHeaders(Board.BOARD_SIZE).printMatrix();
        board.CommonCards[0].printCommonCard();
        board.CommonCards[1].printCommonCard();

        //An array of players is created containing all the information for each one (Bookshelf, Personal Cards, ID and so on...)
        switch (num) {
            case 2: Players = create2Players();
                    break;
            case 3: Players = create3Players();
                    break;
            case 4: Players = create4Players();
                    break;
        }
        //assign to each player a personal card
        PersonalCards = new Card[numPlayers];
        GeneratePersonalCards();

        //TODO: insert methods to print personal cards for each player

    }

    public void begin(){
        Scanner getMove = new Scanner(System.in);
        Random random = new Random();
        int firstPlayerNumber, curr;
        boolean endGame = false;

        //it's decided who will go first
        firstPlayerNumber = random.nextInt(1, numPlayers);
        curr=firstPlayerNumber;
        System.out.println(Players[firstPlayerNumber].username +" will go first");

        //the game starts
        while(!endGame){
            System.out.println(Players[curr].username + " It's your turn!" + " please enter your move:");
            getMove.next();
            //TODO: insert methods from class Player and class Board to allow the player to take tiles from the board and insert them in his bookshelf
            //TODO: then call CommonCards methods to verify if the player has completed one or both
            //TODO: call method to see if the current player's bookshelf is complete, if yes then one last turn
            //TODO: We also have to check if the board has to be refilled
            //TODO: the board and cards will be printed again and the next player can make his move
            endGame=true;
            //I'm not sure if it's necessary printing the personal and common cards every single time since they're always the same
            //maybe implement a command for a player to request them?
        }
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

    private void GeneratePersonalCards(){
        int[] array=new int[numPlayers];
        Random rand=new Random();
        PersonalCard p=new PersonalCard();
        this.PersonalCards=new Card[numPlayers];

        if(this.numPlayers==2) {
            array[0] = rand.nextInt(1, 13);
            array[1] = array[0];
            while (array[1] == array[0])
                array[1] = rand.nextInt(1, 13);
            this.PersonalCards[0]=p.getCard(array[0]);
            this.PersonalCards[1]=p.getCard(array[1]);
        }
        if(this.numPlayers==3){
            array[2]=array[0];
            while (array[2]==array[0] || array[2]==array[1])
                array[2] = rand.nextInt(1, 13);
            this.PersonalCards[2]=p.getCard(array[2]);
        }
        if(this.numPlayers==4){
            array[3]=array[0];
            while (array[3]==array[0] || array[3]==array[1] || array[3]==array[2])
                array[3] = rand.nextInt(1, 13);
            this.PersonalCards[3]=p.getCard(array[3]);
        }
    }
}
