package it.polimi.ingsw;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.PersonalCard;
import it.polimi.ingsw.model.Player;

import java.util.Scanner;
import java.util.Random;

/**
 * This class is used to create and play a match
 * @author Alessandro Fornara
 */
public class Match {
    private final Board board;
    private final int[] arrayId;
    private final String[] arrayUsername;
    private Player[] players;
    private final int numPlayers;
    private Card[] personalCards;
    private int commonPoints1, commonPoints2;

    /**
     * This constructor creates a new board, common goal cards, player's bookshelves, assigns personal cards and everything else necessary to play the game.
     * @author Alessandro Fornara
     * @param num number of players for this match
     */
    Match(int num) {

        numPlayers=num;

        arrayId = new int[numPlayers];
        arrayUsername= new String[numPlayers];

        //The board and common goal cards are created and printed
        board = new Board(numPlayers);
        Scanner getName = new Scanner(System.in);
        System.out.println(Constant.MY_SHELFIE_TITLE);
        printGame();
        for(int i=1; i<numPlayers+1; i++){
            //TODO: all ID must be different
            System.out.println("Inserire ID giocatore " + i + ": ");
            arrayId[i-1]=getName.nextInt();
            System.out.println("Inserire getUsername() giocatore " + i + ": ");
            arrayUsername[i-1]=getName.next();
        }
        //An array of players is created containing all the information for each one (Bookshelf, Personal Cards, ID and so on...)
        switch (num) {
            case 2: players = create2Players();
                    break;
            case 3: players = create3Players();
                    break;
            case 4: players = create4Players();
                    break;
        }
        //assign to each player a personal card
        personalCards = new Card[numPlayers];
        GeneratePersonalCards();

        for(int i=0; i<numPlayers; i++)
            players[i].setPersonalCard(personalCards[i]);

        commonPoints1 = 8;
        commonPoints2 = 8;
    }

    /**
     * This method starts a match allowing the players to play the game.
     * @author Alessandro Fornara
     */
    public void begin(){
        Scanner getMove = new Scanner(System.in);
        Random random = new Random();
        int firstPlayerNumber, lastPlayer, curr, n=0;
        int x1, y1, x2, y2, x3, y3;

        boolean moveOK, endGame = false, exitGame = false;

        //it's decided who will go first
        firstPlayerNumber = random.nextInt(numPlayers);
        curr=firstPlayerNumber;
        //lastPlayer contains the position of the player whose move will end the game
        lastPlayer=(firstPlayerNumber+numPlayers-1)%numPlayers;
        System.out.println(players[curr].getUsername() +" will go first");

        //the game starts
        while(!exitGame){
            moveOK=false;
            n=0;
            while(n!=1 && n!=2 && n!=3) {
                System.out.println("\n" + "Your bookshelf:   Your personal card:");
                printBookshelfAndPersonal(curr);
                System.out.println(players[curr].getUsername() + " It's your turn!" + " please enter how many tiles you want to remove: ");
                n = getMove.nextInt();
            }
            if(n == 1) {
                System.out.println("please enter the coordinates of the tiles you want to remove in the order you want to put them in the bookshelf (from bottom to top): ");
                x1 = getMove.nextInt();
                y1 = getMove.nextInt();
                moveOK= players[curr].pickCard(board, x1, y1);
            }
            else if(n == 2){
                System.out.println("please enter the coordinates of the tiles you want to remove in the order you want to put them in the bookshelf (from bottom to top): ");
                x1 = getMove.nextInt();
                y1 = getMove.nextInt();
                x2 = getMove.nextInt();
                y2 = getMove.nextInt();
                moveOK= players[curr].pickCard(board, x1, y1, x2, y2);
            }
            else if(n == 3){
                System.out.println("please enter the coordinates of the tiles you want to remove in the order you want to put them in the bookshelf (from bottom to top): ");
                x1 = getMove.nextInt();
                y1 = getMove.nextInt();
                x2 = getMove.nextInt();
                y2 = getMove.nextInt();
                x3 = getMove.nextInt();
                y3 = getMove.nextInt();
                moveOK= players[curr].pickCard(board, x1, y1, x2, y2, x3, y3);
            }

            if(moveOK) {
                if(!players[curr].getCommonDone1() && board.getCommonCards()[0].checkBookshelf(players[curr].getMatrixBookshelf())) {
                    System.out.println(players[curr].getUsername() + " has completed the first common goal card");
                    players[curr].calculateCommonPoints(commonPoints1, 1);
                    System.out.println(players[curr].getUsername() + " scored " + commonPoints1 + " points");
                    commonPoints1 = commonPoints1 -2;
                    System.out.println(commonPoints1 + " points will be awarded to the next player who completes this common goal card");

                }
                if(!players[curr].getCommonDone2() && board.getCommonCards()[1].checkBookshelf(players[curr].getMatrixBookshelf())) {
                    System.out.println(players[curr].getUsername() + " has completed the second common goal card");
                    players[curr].calculateCommonPoints(commonPoints2, 2);
                    System.out.println(players[curr].getUsername() + " scored " + commonPoints2 + " points");
                    commonPoints2 = commonPoints2 -2;
                    System.out.println(commonPoints2 + " points will be awarded to the next player who completes this common goal card");
                }

                if(board.isRefillable())
                    board.refill();

                printGame();

                if(players[curr].checkIfFull()){
                    endGame=true;
                }

                if(curr<numPlayers-1)
                    curr++;
                else
                    curr=0;

                if(endGame && curr==lastPlayer){
                    exitGame=true;
                    for(int i=0; i<numPlayers; i++){
                        System.out.println(players[curr].getUsername() + " has scored a total of: " + players[curr].calculatePoints() + " points");
                    }
                    Winner(firstPlayerNumber);
                }

            }else
                System.out.println("That move is not allowed, please try again.");

        }
    }

    /**
     * This method creates 2 players
     * @author Alessandro Fornara
     * @return an Array containing 2 players
     */
    private Player[] create2Players() {
        Player[] playersArray = new Player[2];
        Player player1 = new Player(arrayId[0], arrayUsername[0]);
        Player player2 = new Player(arrayId[1], arrayUsername[1]);

        playersArray[0] = player1;
        playersArray[1] = player2;

        return playersArray;
    }

    /**
     * This method creates 3 players
     * @author Alessandro Fornara
     * @return an Array containing 3 players
     */
    private Player[] create3Players() {
        Player[] playersArray = new Player[3];
        Player player1 = new Player(arrayId[0], arrayUsername[0]);
        Player player2 = new Player(arrayId[1], arrayUsername[1]);
        Player player3 = new Player(arrayId[2], arrayUsername[2]);

        playersArray[0] = player1;
        playersArray[1] = player2;
        playersArray[2] = player3;

        return playersArray;
    }

    /**
     * This method creates 4 players
     * @author Alessandro Fornara
     * @return an Array containing 4 players
     */

    private Player[] create4Players() {
        Player[] playersArray = new Player[4];
        Player player1 = new Player(arrayId[0], arrayUsername[0]);
        Player player2 = new Player(arrayId[1], arrayUsername[1]);
        Player player3 = new Player(arrayId[2], arrayUsername[2]);
        Player player4 = new Player(arrayId[3], arrayUsername[3 ]);

        playersArray[0] = player1;
        playersArray[1] = player2;
        playersArray[2] = player3;
        playersArray[3] = player4;

        return playersArray;
    }

    /**
     * This method generates random Personal Cards which are contained in the class PersonalClass, (all different from one another).
     * @author Alessandro Fornara
     */
    private void GeneratePersonalCards(){
        int[] idPersonalCards = new int[numPlayers];
        Random rand = new Random();
        PersonalCard p = new PersonalCard();
        this.personalCards = new Card[numPlayers];

        if(this.numPlayers==2) {
            idPersonalCards[0] = rand.nextInt(12);
            idPersonalCards[1] = idPersonalCards[0];
            while (idPersonalCards[1] == idPersonalCards[0])
                idPersonalCards[1] = rand.nextInt(12);
            this.personalCards[0] = p.getCard(idPersonalCards[0]);
            this.personalCards[1] = p.getCard(idPersonalCards[1]);
        }
        if(this.numPlayers==3){
            idPersonalCards[2] = idPersonalCards[0];
            while (idPersonalCards[2]==idPersonalCards[0] || idPersonalCards[2]==idPersonalCards[1])
                idPersonalCards[2] = rand.nextInt(12);
            this.personalCards[2] = p.getCard(idPersonalCards[2]);
        }
        if(this.numPlayers==4){
            idPersonalCards[3] = idPersonalCards[0];
            while (idPersonalCards[3]==idPersonalCards[0] || idPersonalCards[3]==idPersonalCards[1] || idPersonalCards[3]==idPersonalCards[2])
                idPersonalCards[3] = rand.nextInt(12);
            this.personalCards[3] = p.getCard(idPersonalCards[3]);
        }
    }

    /**
     * This method prints the board and common goal cards
     * @author Alessandro Fornara
     */
    private void printGame(){
        ItemEnum.generateCharMatrix(board.getMatrix(), Board.BOARD_SIZE, Board.BOARD_SIZE)
                .addNumbering(Board.BOARD_SIZE).appendToAllRows("   ").alignColumn()
                .addOnRight(board.getCommonCards()[0].printCommonCardMatrix()).appendToAllRows("   ").alignColumn()
                .addOnRight(board.getCommonCards()[1].printCommonCardMatrix())
                .printMatrix();
    }

    /**
     * This method prints a player's bookshelf and his personal card
     * @author Alessandro Fornara
     * @param i the index of the array Players
     */
    private void printBookshelfAndPersonal(int i){
        ItemEnum.generateCharMatrix(players[i].getMatrixBookshelf(), 6, 5).appendToAllRows("   ")
                .addOnRight(ItemEnum.generateCharMatrix(personalCards[i].getMatrix(), 6, 5)).printMatrix();
    }

    /**
     * This method decides the winner of the game and prints this player's getUsername()
     * @author Alessandro Fornara
     */
    private void Winner(int first){
        int max=0, pos=0, points, k;
        for(int i=0; i<numPlayers; i++){

            k=(i+first)%numPlayers;
            points= players[k].getMyPoints();

            if(points>=max) {
                max=points;
                pos=k;
            }
        }
        System.out.println(players[pos].getUsername() + " has won the game!");
    }
}